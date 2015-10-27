package tp.pr1.logica;

public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	
	//Constructor de la superficie
	public Superficie(int nf, int nc){
		this.filas=nf;
		this.columnas=nc;
		this.superficie = new Celula[nf][nc];
	}
	
	//Comprueba la disponibilidad de una casilla
	public boolean posLibre(int fila, int columna) {
		return this.superficie[fila][columna] != null;
	}
	
	//Inserta una célula en una posición del tablero
	public boolean insertarCelula(Celula celula, int fila, int columna){
		boolean vacio = this.posLibre(fila,columna);
		if(vacio){
			this.superficie[fila][columna] = celula;
		}
		return vacio;
	}
	
	//Elimina una célula de una posición del tablero
	public void eliminarCelula(int fila, int columna){
		this.superficie[fila][columna] = null;
	}
	
	//Mueve una célula si la posición está disponible
	public boolean moverCelula(int f1, int c1, int f2, int c2) {
			if(this.posLibre(f2,c2)) {			
			Celula cel = this.superficie[f1][c1];
			this.insertarCelula(cel, f2, c2);
			this.eliminarCelula(c1, c1);
			return true;
		}
		else
			return false;
	}
	
	public int getFilas() {
		return this.filas;
	}
	
	public int getColumnas() {
		return this.columnas;
	}
	
}