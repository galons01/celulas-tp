package tp.pr1.comandos;

import java.io.FileNotFoundException;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;;

public class ComandoCargar extends Comando {
	
	private String nombreArchivo;
	
	public ComandoCargar(String nombre) {
		this.nombreArchivo = nombre;
	}
	
	public ComandoCargar() {
		this.nombreArchivo = "unnamed.txt";
	}
	
	public void ejecuta(Controlador cntrl) throws ErrorDeCarga, FileNotFoundException {
		cntrl.cargarMundo(this.nombreArchivo);
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) throws NumeroParametrosIncorrecto {
		if(igualesIns(cadenaComando[0],"CARGAR")){
			if(cadenaComando.length == 2) {
				return new ComandoCargar(cadenaComando[1]);
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
		return "CARGAR: Cargar un mundo de un archivo.";
	}

}
