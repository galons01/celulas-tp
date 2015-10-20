package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.logica.Mundo;

public class Controlador {
	
	//Scanner para leer de teclado (sólo una vez por clase)
	private Scanner in;
	private Mundo mundo;
	
	public Controlador(Mundo mundo, Scanner in) {
		this.in = in;
		//Inicializar mundo
	}
	
	//Comprueba si son iguales (case insensitive)
	private boolean igualesIns(String a, String b) {
		return a.toLowerCase().equals(b.toLowerCase());
	}
	
	public void realizaSimulacion() {
		String comando;
		String[] palabras;
		int f, c;
		
		String mensajeError = "ERROR: Comando desconocido.";
		
		 do {
			 System.out.print("Comando > ");
			 comando = this.in.nextLine();
			 palabras = comando.split(" ");
			 comando = palabras[0];
			 
			 
			 if (palabras.length==1){
					//Siguiente paso
				 if(igualesIns(comando,"PASO")) {
					 
				 }
				 
					//Iniciar simulación
				 else if(igualesIns(comando,"INICIAR")) {

				 }
				 
				//Muestra ayuda
				 else if(igualesIns(comando,"AYUDA")) {
					 
				 }
				 
				//Vacía el mundo
				 else if(igualesIns(comando,"VACIAR")) {
					 
				 }
				 
				 //Si no deseaba salir: ERROR
				 else if(!igualesIns(comando,"SALIR"))  {
					 System.out.println(mensajeError);
				 }
				 
			 }
			 else if(palabras.length==3){
				 f = Integer.parseInt(palabras[1]);
				 c = Integer.parseInt(palabras[2]);
				 
				//Nueva célula
				 if(igualesIns(comando,"CREARCELULA")) {

				 }
				 
					//Elimina una célula
				 else if(igualesIns(comando,"ELIMINARCELULA")) {

				 }
				 
				 else {
					 System.out.println(mensajeError);
				 }
			 }

			 //Comando inválido
			 else {
				 System.out.println(mensajeError);
			 }
			 
		 } while(!igualesIns(comando,"SALIR")); //comando != "salir"
	}
}
