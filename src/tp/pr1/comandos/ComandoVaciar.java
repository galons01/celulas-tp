package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

public class ComandoVaciar extends Comando {

	/**
	 * Ejecuta el comando.
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo) {
		mundo.vaciar();
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoVaciar si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(igualesIns(cadenaComando[0],"VACIAR"))
			return new ComandoVaciar();
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "VACIAR: Vacía el mundo del células.";
	}

}
