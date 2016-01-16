package tp.pr1.control;

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
	
	public Controlador(Mundo mundo, Scanner in) {
		this.in = in;
	}
	
	/**
	 * Comienza la simulacion del mundo. Pide comandos al usuario por medio
	 * de una terminal. La función se mantendrá en bucle mientras no se
	 * introduzca el comando salir.
	 */
	
	public void cargarMundo(String nombreArchivo){
		Scanner archivo = new Scanner(nombreArchivo);
		String primeraLinea = archivo.nextLine();
		int ancho, largo;
		ancho = archivo.nextInt();
		largo = archivo.nextInt();
		if (primeraLinea=="simple"){
			mundo = new MundoSimple(ancho, largo);
			mundo.cargar(archivo);
		}
		else if (primeraLinea=="complejo") {
			mundo = new MundoComplejo(ancho, largo);
			mundo.cargar(archivo);
		}
		else {
			
		}
	}
	
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
			}
		}
		
		System.out.println("Fin de la simulación...");
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
