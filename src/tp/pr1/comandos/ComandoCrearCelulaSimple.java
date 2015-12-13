package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

public class ComandoCrearCelulaSimple extends Comando {
	private int fila;
	private int columna;
	
	public ComandoCrearCelulaSimple(int f, int c) {
		this.fila = f;
		this.columna = c;
	}
	
	public ComandoCrearCelulaSimple() {
		this.fila = this.columna = 0;
	}

	/**
	 * Ejecuta el comando.
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo) {
		if(mundo.crearCelulaSimple(this.fila, this.columna)) { //TODO
			System.out.print("Creamos célula simple en la posición ");
			System.out.println("(" + this.fila + "," + this.columna + ")");
		}
		else
			System.out.println("No es posible crear una célula en esa posición");
	}
	
	
	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoCrearCelulaSimple si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando.length == 3) {
			if(igualesIns(cadenaComando[0],"CREARCELULASIMPLE")) {
				int f = Integer.parseInt(cadenaComando[1]);
				int c = Integer.parseInt(cadenaComando[2]);
				return new ComandoCrearCelulaSimple(f,c);
			}
		}
		return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "CREARCELULASIMPLE: Inserta una célula simple en la posición indicada.";
	}

}
