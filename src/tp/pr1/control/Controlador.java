package tp.pr1.control;

import java.util.Scanner;
import tp.pr1.logica.Mundo;

public class Controlador {
	
	//Scanner para leer de teclado (sólo una vez por clase)
	private Scanner in;
	private Mundo mundo;
	
	public Controlador(/*Mundo mundo,*/ Scanner in) {
		this.in = in;
		//Inicializar mundo
	}
	
	//Comprueba si son iguales (case insensitive)
	private boolean igualesIns(String a, String b) {
		return a.toLowerCase().equals(b.toLowerCase());
	}
	
	public void realizaSimulacion() {
		String comando;
		int f, c;
		 do {
			 System.out.print("Comando > ");
			 comando = this.in.next();
			 
			//Siguiente paso
			 if(igualesIns(comando,"PASO")) {
				 
			 }
			 
			//Iniciar simulación
			 else if(igualesIns(comando,"INICIAR")) {

			 }
			 
			//Nueva célula
			 else if(igualesIns(comando,"CREARCELULA")) {

				 f = this.in.nextInt();
				 c = this.in.nextInt();
			 }
			 
			//Elimina una célula
			 else if(igualesIns(comando,"ELIMINARCELULA")) {

				 f = this.in.nextInt();
				 c = this.in.nextInt();
			 }
			 
			//Muestra ayuda
			 else if(igualesIns(comando,"AYUDA")) {
				 
			 }
			 
			//Vacía el mundo
			 else if(igualesIns(comando,"VACIAR")) {
				 
			 }
			 
			//Abandonar simulación
			 else if(igualesIns(comando,"SALIR")) {
				 //En principio nada por aquí.
				 //Borrar si no es necesario
			 }
			 
			 //Comando inválido
			 else {
				 System.out.println("ERROR: Comando desconocido.");
				 comando = "";
			 }
			 
		 } while(!igualesIns(comando,"SALIR")); //comando != "salir"
	}
}
