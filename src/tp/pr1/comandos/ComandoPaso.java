package tp.pr1.comandos;

import tp.pr1.control.Controlador;

public class ComandoPaso extends Comando {

	/**
	 * Ejecuta el comando.
	 * @param mundo
	 */
	public void ejecuta(Controlador cntrl) {
		cntrl.evoluciona();
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoPaso si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(igualesIns(cadenaComando[0],"PASO"))
			return new ComandoPaso();
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "PASO: Da un paso en la evolución del mundo.";
	}

}
