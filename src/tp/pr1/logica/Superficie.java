package tp.pr1.logica;

public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	private int nCelulas = 0;
	
	//Constructor de la superficie
	public Superficie(int nf, int nc){
		this.filas=nf;
		this.columnas=nc;
		this.superficie = new Celula[nf][nc];
	}
	
	//Comprueba la disponibilidad de una casilla
	public boolean posLibre(int fila, int columna) {
		return this.superficie[fila][columna] == null;
	}
	
	//Inserta una célula en una posición del tablero
	public boolean insertarCelula(Celula celula, int fila, int columna){
		boolean libre = this.posLibre(fila,columna);
		if(libre){
			this.superficie[fila][columna] = celula;
			this.nCelulas++;
		}
		return libre;
	}
	
	//Elimina una célula de una posición del tablero
	public void eliminarCelula(int fila, int columna){
		this.superficie[fila][columna] = null;
		this.nCelulas--;
	}
	
	//Mueve una célula si la posición está disponible
	public boolean moverCelula(int f1, int c1, int f2, int c2) {
		if(this.posLibre(f2,c2)) {			
			Celula cel = this.superficie[f1][c1];
			this.insertarCelula(cel, f2, c2);
			this.eliminarCelula(f1, c1);
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
	
	public int nCelulas() {
		return this.nCelulas;
	}
	
	
	/************************************************
	*  Funciones puente entre el mundo y la célula  *
	************************************************/
	
	public boolean puedeMoverse(int f, int c) {
		return this.superficie[f][c].puedeMoverse();
	}
	
	public boolean puedeReprod(int f, int c) {
		return this.superficie[f][c].puedeReprod();
	}
	public boolean reproducir(int f, int c) {
		return this.superficie[f][c].reproducir();
	}
	
	public void estarQuieta(int f, int c) {
		this.superficie[f][c].estarQuieta();
	}
	
	public void darPaso(int f, int c) {
		this.superficie[f][c].darPaso();
	}
	
	public int getPasosReprod(int f, int c) { 
		return this.superficie[f][c].getPasosReprod();
	}

	public int getPasosMuerte(int f, int c) {
		return this.superficie[f][c].getPasosMuerte();
	}
	
}