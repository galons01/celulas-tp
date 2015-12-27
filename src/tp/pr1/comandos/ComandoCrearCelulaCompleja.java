package tp.pr1.comandos;

import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.IndicesFueraDeRango;
import tp.pr1.logica.Mundo;

public class ComandoCrearCelulaCompleja extends Comando {
	private int fila;
	private int columna;
	
	public ComandoCrearCelulaCompleja(int f, int c) {
		this.fila = f;
		this.columna = c;
	}
	
	public ComandoCrearCelulaCompleja() {
		this.fila = this.columna = 0;
	}

	
	public void ejecuta(Mundo mundo) throws IndicesFueraDeRango {
		try {
			if(mundo.crearCelulaCompleja(this.fila, this.columna)) {
				System.out.print("Creamos célula compleja en la posición (");
				System.out.println( this.fila + "," + this.columna + ")");
			}
			else System.out.println("Ya hay una célula en esa posición");
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new IndicesFueraDeRango();
		}
	}
	
	
	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoCrearCelulaCompleja si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if( cadenaComando.length == 3 &&
			igualesIns(cadenaComando[0],"CREARCELULACOMPLEJA")) {
			try {
				int f = Integer.parseInt(cadenaComando[1]);
				int c = Integer.parseInt(cadenaComando[2]);
				return new ComandoCrearCelulaCompleja(f,c);
			} catch ( NumberFormatException e ) {
				throw new  FormatoNumericoIncorrecto();
			}
		}
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "CREARCELULACOMPLEJA: Inserta una célula compleja en la posición indicada.";
	}

}
