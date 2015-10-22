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
	public boolean posDisponible(int fila, int columna) {
		return this.superficie[fila][columna] != null;
	}
	
	//Inserta una célula en una posición del tablero
	public boolean insertarCelula(Celula celula, int fila, int columna){
		boolean vacio = this.posDisponible(fila,columna);
		if(vacio){
			this.superficie[fila][columna] = celula;
		}
		return vacio;
	}
	
	//Elimina una célula de una posición del tablero
	public void eliminarCelula(int fila, int columna){
		this.superficie[fila][columna] = null;
	}
	
	//Getter de de células
	public Celula getCelula(int fila, int col) {
		return this.superficie[fila][col];
	}
	
}