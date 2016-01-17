package tp.pr1.comandos;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.IndicesFueraDeRango;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;

public class ComandoEliminarCelula extends Comando {
	private int fila;
	private int columna;
	
	public ComandoEliminarCelula() {
		this.fila = this.columna = 0;
	}
	
	public ComandoEliminarCelula(int f, int c) {
		this.fila = f;
		this.columna = c;
	}
	

	/**
	 * Ejecuta el comando eliminarcelula. Elimina una celula del mundo.
	 * @param mundo Mundo sobre el que se ejecuta la acción.
	 */
	public void ejecuta(Controlador cntrl) throws IndicesFueraDeRango {
		try {
			if(cntrl.eliminarCelula(this.fila, this.columna)) {
				System.out.print("Eliminamos la célula en la posición (");
				System.out.println( this.fila + "," + this.columna + ")");
			}
			else System.out.println("No hay una célula en esa posición");
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new IndicesFueraDeRango();
		}
	}
	
	
	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoCrearEliminarCelula si procede o null.
	 * @throws FormatoNumericoIncorrecto 
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto, NumeroParametrosIncorrecto {
		if(igualesIns(cadenaComando[0],"ELIMINARCELULA") ) {
			if(cadenaComando.length == 3) {
				try {
					int f = Integer.parseInt(cadenaComando[1]);
					int c = Integer.parseInt(cadenaComando[2]);
					return new ComandoEliminarCelula(f,c);
				} catch(NumberFormatException e) {
					throw new FormatoNumericoIncorrecto();
				}
			} else throw new NumeroParametrosIncorrecto();
		}
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "ELIMINARCELULA: Eliminar una celula en una posición.";
	}

}
