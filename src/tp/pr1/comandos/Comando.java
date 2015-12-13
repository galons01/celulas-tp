package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

public abstract class Comando {
	
	/**
	 * Ejecuta las acciones del comando.
	 * @param mundo Mundo sobre el que se ejecuta la acción.
	 */
	public abstract void ejecuta(Mundo mundo);
	
	
	/**
	 * Parsea el comando, de forma que si los parámetros 
	 * coinciden, se devuelve el comando.
	 * @param cadenaComando Cadena con los parámetros del comando.
	 * @return Comando de la subclase que coincida.
	 */
	public abstract Comando parsea(String[ ] cadenaComando);
	
	
	/**
	 * Genera la ayuda del comando.
	 * @return String con el texto de ayuda.
	 */
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
