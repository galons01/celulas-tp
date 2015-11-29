package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.comandos.Comando;
import tp.pr1.comandos.ParserComandos;
import tp.pr1.logica.Mundo;

public class Controlador {
	
	//Scanner para leer de teclado (sólo una vez por clase)
	private Scanner in;
	private Mundo mundo;
	
	public Controlador(Mundo mundo, Scanner in) {
		this.in = in;
		this.mundo = mundo;
	}
	
	
	public void realizaSimulacion() {
		Comando comando;
		String[] palabras;
		
		 do {
			 System.out.print("Comando > ");

			 palabras = this.in.nextLine().split(" ");
			 comando = ParserComandos.parseaComando(palabras);
			 
			 if(comando != null) {
				 comando.ejecuta(this.mundo);
			 }
			 //Comando inválido
			 else {
				 System.out.println("ERROR: Comando desconocido.");
			 }
			 
			 System.out.println(this.mundo.toString());
			 
		 } while(!Comando.igualesIns(palabras[0],"SALIR")); //comando != "salir"
		 
		 System.out.println("Fin de la simulación...");
	}
	
}
