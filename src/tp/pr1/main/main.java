package tp.pr1.main;

import tp.pr1.control.Controlador;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Controlador ctrl = new Controlador(sc);
		
		ctrl.realizaSimulacion();	
	}

}
