package tp.pr1.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public Controlador(Scanner in) {
		this.in = in;
	}
	
	/**
	 * Carga un mundo de un archivo.
	 * @param nombreArchivo Nombre del archivo a cargar.
	 * @throws ErrorCargar Si el archivo está corrupto.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	public void cargarMundo(String nombreArchivo) throws ErrorCargar, FileNotFoundException {
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
			else throw new ErrorCargar();
			mundo.cargar(archivo);
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
		FileWriter file = new FileWriter(new File(nombreArchivo));
		this.mundo.guardar(file);
	}
	
	/**
	 * Comienza la simulacion del mundo. Pide comandos al usuario por medio de
	 * una terminal. La función se mantendrá en bucle mientras no se introduzca
	 * el comando salir.
	 */
	public void realizaSimulacion() {
		Comando comando;
		String[] palabras;
		
		while(mundo.simulacionTerminada()) {
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
			}
		}
		
		System.out.println("Fin de la simulación...");
	}
	public String readWord() {
		return this.in.next();
	}
	
	//===================================
	// Funciones puente                 |
	//===================================
	
	public boolean crearCelula(Celula cel, int f, int c) throws IndicesFueraDeRango {
		return this.mundo.crearCelula(cel, f, c);
	}
	
	public boolean eliminarCelula(int f, int c) throws IndicesFueraDeRango {
		return this.mundo.eliminarCelula(f, c);
	}
	
	public void iniciar() throws ErrorDeInicializacion {
		this.mundo.iniciar();
	}
	
	public void evoluciona() {
		this.mundo.evoluciona();
	}
	
	public void terminarSimulacion() {
		this.mundo.terminarSimulacion();
	}
	
	public void vaciar() {
		this.mundo.vaciar();
	}
}
