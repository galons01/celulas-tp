package tp.pr1.logica;

public class Casilla {
	private int col = 0;
	private int fila = 0;
	
	public Casilla (int f, int c) {
		this.fila = f;
		this.col = c;
	}

	public int getColumna() {
		return this.col;
	}
	
	public int getFila() {
		return this.fila;
	}
	
	public void setFila(int f) {
		this.fila = f;
	}
	
	public void setColumna(int c) {
		this.fila = c;
	}
	
}
