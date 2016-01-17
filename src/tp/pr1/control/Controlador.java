package tp.pr1.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tp.pr1.comandos.Comando;
import tp.pr1.comandos.ParserComandos;

import tp.pr1.excepciones.*;

import tp.pr1.logica.Celula;
import tp.pr1.logica.Mundo;
import tp.pr1.logica.MundoComplejo;
import tp.pr1.logica.MundoSimple;

public class Controlador {
	
	//Scanner para leer de teclado (sólo una vez por clase)
	private Scanner in;
	private Mundo mundo;
	private boolean simulacionEnCurso = true;
	/**
	 * nuevo constructo para el controlador
	 * @param in un escaner de entrada
	 */
	public Controlador(Scanner in) {
		this.in = in;
		this.mundo = new MundoSimple(3,4);
		try {
			this.mundo.iniciar();
		}
		catch (ErrorDeInicializacion e) {
			//Do nothing. Safe tested configuration.
		}
	}
	
	/**
	 * Carga un mundo de un archivo.
	 * @param nombreArchivo Nombre del archivo a cargar.
	 * @throws ErrorDeCarga Si el archivo está corrupto.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	public void cargarMundo(String nombreArchivo) throws ErrorDeCarga, FileNotFoundException {
		Scanner archivo;
		String tMundo;
		int filas, columnas;
		Mundo nuevoMundo;
		
		archivo = new Scanner(new File(nombreArchivo));
		
		try {
			tMundo = archivo.nextLine();
			filas = archivo.nextInt();
			columnas = archivo.nextInt();
			
			if(tMundo.equals("simple")) {
				nuevoMundo = new MundoSimple(filas, columnas);
			}
			else if(tMundo.equals("complejo")) {
				nuevoMundo = new MundoComplejo(filas, columnas);
			}
			else throw new ErrorDeCarga();
			nuevoMundo.cargar(archivo);
		} catch (NoSuchElementException e) {
			throw new ErrorDeCarga();
		}
		finally {
			archivo.close();
		}
		this.mundo = nuevoMundo;
	}
	
	/**
	 * Guarda un mundo de un archivo.
	 * @param nombreArchivo Nombre del archivo a guardar.
	 * @throws IOException 
	 */
	public void guardarMundo(String nombreArchivo) throws IOException {
		FileWriter archivo = new FileWriter(nombreArchivo);
		this.mundo.guardar(archivo);
		archivo.close();
	}
	
	/**
	 * Comienza la simulacion del mundo. Pide comandos al usuario por medio de
	 * una terminal. La función se mantendrá en bucle mientras no se introduzca
	 * el comando salir.
	 */
	public void realizaSimulacion() {
		Comando comando;
		String[] palabras;
		
		while(simulacionEnCurso) {
			System.out.print(this.mundo.toString());
			System.out.print("Comando > ");

			palabras = this.in.nextLine().split(" ");
			try {
				comando = ParserComandos.parseaComando(palabras);
				if(comando != null) {
					comando.ejecuta(this);
				}
				else {
					System.out.println("ERROR: Comando desconocido.");
				}
			} catch(IndicesFueraDeRango e) {
				System.out.println(e.getMessage());
			} catch(FormatoNumericoIncorrecto e) {
				System.out.println(e.getMessage());
			} catch (ErrorDeInicializacion e) {
				System.out.println(e.getMessage());
			} catch (PalabraIncorrecta e) {
				System.out.println(e.getMessage());
			} catch (ErrorDeCarga e) {
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				System.out.println("EXCEPCIÓN: Archivo no encontrado.");
			} catch (IOException e) {
				System.out.println("EXCEPCIÓN: Error al guardar.");
			} catch (NumeroParametrosIncorrecto e) {
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println("Fin de la simulación...");
	}
	
	/**
	 * Devuelve el siguiente comando a leer.
	 * @return String con el siguiente comando.
	 */
	public String siguienteComando() {
		return this.in.nextLine();
	}
	
	//===================================
	// Funciones puente                 |
	//===================================
	
	/**
	 * crea una celula (funcion puente)
	 * @param cel celula que se va a crear
	 * @param f fila donde se creara
	 * @param c columna donde se creara
	 * @return si no se crea retorna false
	 * @throws IndicesFueraDeRango
	 */
	public boolean crearCelula(Celula cel, int f, int c) throws IndicesFueraDeRango {
		return this.mundo.crearCelula(cel, f, c);
	}
	
	
	/**
	 * elimina la celula de una posicion (funcion puente)
	 * @param f fila donde esta la celula
	 * @param c columna donde esta la celula
	 * @return devuelve si se borro correctamente un true sino un false
	 * @throws IndicesFueraDeRango
	 */
	public boolean eliminarCelula(int f, int c) throws IndicesFueraDeRango {
		return this.mundo.eliminarCelula(f, c);
	}
	
	/**
	 * inicia el mundo
	 * @throws ErrorDeInicializacion
	 */
	public void iniciar() throws ErrorDeInicializacion {
		this.mundo.iniciar();
	}
	
	/**
	 * vacia el mundo dejandolo sin celulas
	 */
	public void vaciar() {
		this.mundo.vaciar();
	}
	/**
	 * da un paso
	 */
	public void evoluciona() {
		this.mundo.evoluciona();
	}
	
	/**
	 * termina la simulacion
	 */
	public void terminarSimulacion() {
		this.simulacionEnCurso = false;
	}
	
	/**
	 * Crea un nuevo mundo en el juego.
	 * @param tMundo Nombre del tipo del mundo
	 * @param f Número de filas
	 * @param c Número de columnas.
	 * @param nCelulas Array con el número de células. Cada posición es un tipo de célula.
	 * @throws PalabraIncorrecta Si no se reconoce el tipo de mundo.
	 * @throws NumeroParametrosIncorrecto 
	 */
	public void nuevoMundo(String tMundo, int f, int c, int[] nCelulas) throws PalabraIncorrecta, NumeroParametrosIncorrecto {
		try {
			if(Comando.igualesIns(tMundo, "simple")) {
				this.mundo = new MundoSimple(f,c,nCelulas[0]);
			} else if(Comando.igualesIns(tMundo, "complejo")) {
				this.mundo = new MundoComplejo(f,c,nCelulas[0], nCelulas[1]);
			}
			else throw new PalabraIncorrecta(tMundo);
		} catch(IndexOutOfBoundsException e) {
			throw new NumeroParametrosIncorrecto();
		}
	}
	
}
