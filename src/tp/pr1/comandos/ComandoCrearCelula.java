package tp.pr1.comandos;

import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.IndicesFueraDeRango;
import tp.pr1.logica.CelulaCompleja;
import tp.pr1.logica.CelulaSimple;
import tp.pr1.logica.Mundo;

public class ComandoCrearCelula extends Comando {
	private int fila;
	private int columna;
	
	public ComandoCrearCelula(int f, int c) {
		this.fila = f;
		this.columna = c;
	}
	
	public ComandoCrearCelula() {
		this.fila = this.columna = 0;
	}


	public void ejecuta(Mundo mundo) throws IndicesFueraDeRango {
		if(mundo.crearCelula(new CelulaCompleja(),this.fila, this.columna)) {
			System.out.print("Creamos célula compleja en la posición (");
			System.out.println( this.fila + "," + this.columna + ")");
		}
		else System.out.println("Ya hay una célula en esa posición");
	}
	
	
	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoCrearCelula si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		if( cadenaComando.length == 3 &&
			igualesIns(cadenaComando[0],"CREARCELULA")) {
			try {
				int f = Integer.parseInt(cadenaComando[1]);
				int c = Integer.parseInt(cadenaComando[2]);
				return new ComandoCrearCelula(f,c);
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
		return "CREARCELULA: Inserta una célula en la posición indicada.";
	}

}
