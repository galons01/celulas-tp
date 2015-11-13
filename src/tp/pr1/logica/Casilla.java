package tp.pr1.logica;

public class Casilla {
	private int fila = 0; 	/*fila en la que se posiciona*/
	private int col = 0;	/*columna en la que se posiciona*/
	
	public Casilla (int f, int c) {
		this.fila = f;
		this.col = c;
	}
	// copia una casilla 
	public void copiar(Casilla c) {
		c.fila = this.fila;
		c.col = this.col;
	}
	//  obtener la columna
	public int getColumna() {
		return this.col;
	}
	//  obtiene la fila
	public int getFila() {
		return this.fila;
	}

	
}
