package tp.pr1.comandos;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.ErrorCargar;
import tp.pr1.excepciones.FormatoNumericoIncorrecto;;

public class ComandoCargar extends Comando {
	
	private String nombreArchivo;
	
	public ComandoCargar(String nombre) {
		this.nombreArchivo = nombre;
	}
	
	public ComandoCargar() {
		this.nombreArchivo = "unnamed.txt";
	}
	
	public void ejecuta(Controlador cntrl) throws ErrorCargar, FileNotFoundException {
		cntrl.cargarMundo(this.nombreArchivo);
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if( cadenaComando.length == 2 &&
			igualesIns(cadenaComando[0],"CARGAR")) {
				return new ComandoCargar(cadenaComando[1]);
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
