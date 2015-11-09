package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.logica.Mundo;

public void ayuda(){
		System.out.println("PASO: Realiza un movimiento sobre cada una de las células del mundo, respetando" + "las reglas de evolución de éste.");
		System.out.println("INICIAR: Inicia la simulación. Es decir inicializa el mundo eliminando las células anteriores de la superficie e introduciendo nuevas células en posiciones aleatorias.");
		System.out.println("El número de células vendrá especificado por una constante.");
		System.out.println("CREARCELULA f c: Crea una nueva célula y la introduce en el mundo en la posición (f,c). Si la posición está ocupada manda un mensaje de error.");
		System.out.println("ELIMINARCELULA f c: Elimina la célula del mundo en la posición (f,c). En caso de que dicha posición estuviera vacía manda un mensaje de error.");
		System.out.println("AYUDA: Muestra los distintos comandos que se pueden ejecutar en la Práctica 1, junto con una breve descripción de lo que hacen.");
		System.out.println("VACIAR: Elimina todas las células del mundo.");
		System.out.println("SALIR: Es una metainstrucción que nos permite abandonar la simulación");
	}

public class Controlador {
	
	//Scanner para leer de teclado (sólo una vez por clase)
	private Scanner in;
	private Mundo mundo;
	
	public Controlador(Mundo mundo, Scanner in) {
		this.in = in;
		this.mundo = mundo;
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
					 mundo.evoluciona();
				 }
				 
					//Iniciar simulación
				 else if(igualesIns(comando,"INICIAR")) {
					 mundo.iniciar();
				 }
				 
				//Muestra ayuda
				 else if(igualesIns(comando,"AYUDA")) {
					 ayuda();
				 }
				 
				//Vacía el mundo
				 else if(igualesIns(comando,"VACIAR")) {
					 mundo.vaciar();
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
