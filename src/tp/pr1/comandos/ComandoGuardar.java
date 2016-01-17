package tp.pr1.comandos;

import java.io.IOException;

import tp.pr1.control.Controlador;;

public class ComandoGuardar extends Comando {
	
	private String nombreArchivo;
	
	public ComandoGuardar(String nombre) {
		this.nombreArchivo = nombre;
	}
	
	public ComandoGuardar() {
		this.nombreArchivo = "unnamed.txt";
	}
	
	public void ejecuta(Controlador cntrl) throws IOException {
		cntrl.guardarMundo(this.nombreArchivo);
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if( cadenaComando.length == 2 &&
			igualesIns(cadenaComando[0],"GUARDAR")) {
					return new ComandoGuardar(cadenaComando[1]);
		}
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
