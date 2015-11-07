package tp.pr1.logica;

public class Casilla {
	private int fila = 0;
	private int col = 0;
	
	public Casilla (int f, int c) {
		this.fila = f;
		this.col = c;
	}
	public void copiar(Casilla c) {
		c.fila = this.fila;
		c.col = this.col;
	}

	public int getColumna() {
		return this.col;
	}
	
	public int getFila() {
		return this.fila;
	}

	
}
