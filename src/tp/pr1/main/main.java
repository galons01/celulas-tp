package tp.pr1.main;

import tp.pr1.control.Controlador;
import tp.pr1.logica.Mundo;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Mundo mundo = new Mundo(); //Inicializar aquí porque si no da error controlador constructor.
		
		Controlador ctrl = new Controlador(mundo, sc);
		
		ctrl.realizaSimulacion();	
	}

}
