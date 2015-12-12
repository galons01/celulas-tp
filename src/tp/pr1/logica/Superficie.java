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
		this.vaciar();
	}
	
	/*Limpia la superficie dejándola sin celulas.*/
	public void vaciar(){
		for (int i=0; i<this.filas; i++)
			for (int j=0; j<this.columnas; j++)
				this.superficie[i][j] = null;
	}
	
	
	//Comprueba la disponibilidad de una casilla
	public boolean posLibre(int fila, int columna) {
		return this.superficie[fila][columna] == null;
	}
	
	//Inserta una célula en una posición del tablero
	public void insertarCelula(Celula celula, int fila, int columna){
		if(this.superficie[fila][columna]==null)
			this.nCelulas++;
		this.superficie[fila][columna] = celula;
	}
	
	//Elimina una célula de una posición del tablero
	public void eliminarCelula(int fila, int columna){
		if(this.superficie[fila][columna]!=null) {
			this.superficie[fila][columna] = null;
			this.nCelulas--;
		}
	}
	
	//Mueve una célula si la posición está disponible
	public void moverCelula(int f1, int c1, int f2, int c2) {			
		this.insertarCelula(this.superficie[f1][c1], f2, c2);
		this.eliminarCelula(f1, c1);
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
	
	/*Posición válida dentro de la superficie*/
	public boolean posValida(int f, int c) {
		return f>=0 && f<this.filas && c>=0 && c<this.columnas;
	}
	
	
	
	/**
	 * Representa en un String la superficie.
	 */
	public String toString() {
		StringBuilder mostrar = new StringBuilder();
		for(int i = 0; i< this.filas; i++) {
			for(int j = 0; j< this.columnas; j++) {
				if(this.posLibre(i,j)) {
					mostrar.append("  -  ");
				}
				else {
					mostrar.append(this.superficie[i][j]);
				}
				mostrar.append(" ");
			}
			mostrar.append("\n");
		}
		return mostrar.toString();
	}

	/************************************************
	*  Funciones puente entre el mundo y la célula  *
	************************************************/
	
	public Casilla ejecutaMovimiento(Casilla pos){
		int f = pos.getFila();
		int c = pos.getColumna();
		return this.superficie[f][c].ejecutaMovimiento(f, c, this);
	}
	
	public boolean esComestible(int f, int c) {
		if(this.superficie[f][c]!=null)
			return this.superficie[f][c].esComestible();
		else return false;
	}
	
}
