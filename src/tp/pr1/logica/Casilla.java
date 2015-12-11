package tp.pr1.logica;

public class Casilla {
	private int fila = 0;
	private int col = 0;
	
	public Casilla (int f, int c) {
		this.fila = f;
		this.col = c;
	}
	
	/**
	 * Copia una casilla en la casilla actual
	 * @param c Casilla a copiar
	 */
	public void set(Casilla c) {
		this.fila = c.fila;
		this.col = c.col;
	}
	
	/**
	 * Getter de la columna
	 * @return Un entero representando una columna
	 */
	public int getColumna() {
		return this.col;
	}
	
	/**
	 * Getter de la fila
	 * @return Un entero representando una fila
	 */
	public int getFila() {
		return this.fila;
	}

	
}
