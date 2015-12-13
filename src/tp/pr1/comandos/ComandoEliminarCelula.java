package tp.pr1.comandos;

import tp.pr1.logica.Mundo;

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
	 * Ejecuta el comando.
	 * @param mundo
	 */
	public void ejecuta(Mundo mundo) {
		if(mundo.eliminarCelula(this.fila, this.columna)) {
			System.out.print("Eliminamos célula en la posición (");
			System.out.println( this.fila + "," + this.columna + ")");
		}
		else
			System.out.println("Posición inválida");
	}
	
	
	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoCrearEliminarCelula si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) {
		if(cadenaComando.length == 3) {
			if(igualesIns(cadenaComando[0],"ELIMINARCELULA")) {
				int f = Integer.parseInt(cadenaComando[1]);
				int c = Integer.parseInt(cadenaComando[2]);
				return new ComandoEliminarCelula(f,c);
			}
		}
		return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "ELIMINARCELULA: Elimina la célula en la posición indicada.";
	}

}
