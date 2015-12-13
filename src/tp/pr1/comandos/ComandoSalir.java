package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

public class ComandoSalir extends Comando {

	/**
	 * Ejecuta el comando.
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo) {
		mundo.terminarSimulacion();
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoSalir si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(igualesIns(cadenaComando[0],"SALIR"))
			return new ComandoSalir();
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "INICIAR: Inserta algunas células aleatorias en el mundo.";
	}

}
