package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

public class ComandoAyuda extends Comando {

	/**
	 * Ejecuta el comando.
	 * @param mundo (Not needed)
	 */
	public void ejecuta(Mundo mundo) {
		System.out.print(ParserComandos.AyudaComandos());
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoAyuda si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(igualesIns(cadenaComando[0],"AYUDA"))
			return new ComandoAyuda();
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "AYUDA: Muestra los comandos disponibles y una breve descripción de lo que hacen";
	}

}
