package tp.pr1.logica;


public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	private int nCelulas = 0;
	
	public Superficie(int nf, int nc){
		this.filas=nf;
		this.columnas=nc;
		this.superficie = new Celula[nf][nc];
		this.vaciar();
	}
	
	
	/**
	 * Limpia la superficie dejándola sin celulas.
	 */
	public void vaciar(){
		for (int i=0; i<this.filas; i++)
			for (int j=0; j<this.columnas; j++)
				this.superficie[i][j] = null;
	}
	
	
	/**
	 * Comprueba la disponibilidad de una casilla
	 * @param fila Fila dentro de la superficie
	 * @param columna Columna dentro de la superficie
	 * @return True si está libre, false en caso contrario
	 */
	public boolean posLibre(int fila, int columna) {
		return this.superficie[fila][columna] == null;
	}
	

	/**
	 * Inserta una célula en una posición de la superficie
	 * @param f Fila en la superficie
	 * @param c Columna en la superficie
	 */
	public void insertarCelula(Celula celula, int fila, int columna){
		if(this.superficie[fila][columna]==null)
			this.nCelulas++;
		this.superficie[fila][columna] = celula;
	}
	
	
	/**
	 * Elimina una célula en una posición de la superficie
	 * @param f Fila en la superficie
	 * @param c Columna en la superficie
	 */
	public void eliminarCelula(int fila, int columna){
		if(this.superficie[fila][columna]!=null) {
			this.superficie[fila][columna] = null;
			this.nCelulas--;
		}
	}
	
	
	/**
	 * Mueve una célula de una posición de la superficie a otra
	 * @param f1 Fila de origen en la superficie
	 * @param c1 Columna de origne en la superficie
	 * @param f2 Fila destino en la superficie
	 * @param c2 Columna destino en la superficie
	 */
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
	 * Devuelve una posición aleatoria de la superficie.
	 * @return Casilla aleatoria.
	 */
	public Casilla posAleatoria(){
		int f = Mundo.numAleatorio(0,this.filas-1);
		int c = Mundo.numAleatorio(0,this.columnas-1);
		return new Casilla(f,c);
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
