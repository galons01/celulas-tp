package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

public abstract class Comando {
	
	public abstract void ejecuta(Mundo mundo);
	public abstract Comando parsea(String[ ] cadenaComando);
	public abstract String textoAyuda();
	
	
	/**
	 * Comprueba si dos String son iguales (case insensitive)
	 * @param a String 1
	 * @param b String 2
	 * @return true si son iguales, false si no lo son
	 */
	public static boolean igualesIns(String a, String b) {
		return a.toLowerCase().equals(b.toLowerCase());
	}
}
