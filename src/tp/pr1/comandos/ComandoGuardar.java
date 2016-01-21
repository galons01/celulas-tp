package tp.pr1.comandos;

import java.io.IOException;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;;

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
	public Comando parsea(String[] cadenaComando) throws NumeroParametrosIncorrecto {
		if(igualesIns(cadenaComando[0],"GUARDAR")) {
			if(cadenaComando.length == 2) {
				return new ComandoGuardar(cadenaComando[1]);
			}
			else throw new NumeroParametrosIncorrecto();
		}
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "GUARDAR: Guardar el mundo en un arhivo. ";
	}

}
