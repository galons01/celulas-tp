package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;

import tp.pr1.excepciones.IndicesFueraDeRango;

public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	private int nElems = 0;
	
	public Superficie(int nf, int nc){
		this.filas=nf;
		this.columnas=nc;
		this.superficie = new Celula[nf][nc];
		this.vaciar();
	}
	
	
	/**
	 * Limpia la superficie dejándola vacía.
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
	 * Inserta un elemento en una posición de la superficie
	 * @param elem Elemento a insertar en la superficie.
	 * @param f Fila en la superficie
	 * @param c Columna en la superficie
	 * @throws IndicesFueraDeRango Si la posición está fuera de la superficie
	 */
	public void insertar(Celula elem, int fila, int columna) throws IndicesFueraDeRango{
		try {
			if(this.superficie[fila][columna]==null)
				this.nElems++;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndicesFueraDeRango();
		}
		this.superficie[fila][columna] = elem;
	}
	
	
	/**
	 * Elimina un elemento en una posición de la superficie
	 * @param f Fila en la superficie
	 * @param c Columna en la superficie
	 * @throws IndicesFueraDeRango Si la posición está fuera de la superficie
	 */
	public void eliminar(int fila, int columna) throws IndicesFueraDeRango{
		try {
			if(this.superficie[fila][columna]!=null) {
				this.superficie[fila][columna] = null;
				this.nElems--;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndicesFueraDeRango();
		}
	}
	
	
	/**
	 * Mueve un elemento de una posición de la superficie a otra
	 * @param f1 Fila de origen en la superficie
	 * @param c1 Columna de origne en la superficie
	 * @param f2 Fila destino en la superficie
	 * @param c2 Columna destino en la superficie
	 * @throws IndicesFueraDeRango Si la posición está fuera de la superficie
	 */
	public void mover(int f1, int c1, int f2, int c2) throws IndicesFueraDeRango {			
		this.insertar(this.superficie[f1][c1], f2, c2);
		this.eliminar(f1, c1);
	}
	
	
	/**
	 * Getter filas. Devuelve el número de filas que hay en la superficie.
	 * @return Entero indicando las filas.
	 */
	public int getFilas() {
		return this.filas;
	}
	
	
	/**
	 * Getter columnas. Devuelve el número de columnas que hay en la superficie.
	 * @return Entero indicando las columnas.
	 */
	public int getColumnas() {
		return this.columnas;
	}
	
	
	/**
	 * Devuelve el número de elementos dentro de la superficie.
	 * @return Entero indicando el número de elementos.
	 */
	public int nElems() {
		return this.nElems;
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
	
	
	public boolean hayCapacidad(int n) {
		return (this.filas*this.columnas)-this.nElems >= n;
	}
	
	
	/************************************************
	*  Funciones puente entre el mundo y la célula  *
	************************************************/
	
	
	/**
	 * Ejecuta la lógica de una célula en la superficie
	 * @param f Fila en la que se ebcuentra la célula dentro de la superficie
	 * @param c Columna en la que se ebcuentra la célula dentro de la superficie
	 * @param superficie Superficie en la que se encuentra la célula
	 * @return Casilla a la que se ha movido o null.
	 * @throws IndicesFueraDeRango Si la posición está fuera de la superficie
	 */
	public Casilla ejecutaMovimiento(Casilla pos) throws IndicesFueraDeRango{
		int f = pos.getFila();
		int c = pos.getColumna();
		return this.superficie[f][c].ejecutaMovimiento(f, c, this);
	}
	
	
	/**
	 * Determina si la célula en la posición (f,c) es comestible o no.
	 * @return true si es comestible, false en caso contrario
	 */
	public boolean esComestible(int f, int c) {
		if(this.superficie[f][c]!=null)
			return this.superficie[f][c].esComestible();
		else return false;
	}
	
	/**
	 * Guarda la superficie en un archivo.
	 * @param file Archivo al que va la superficie.
	 * @throws IOException
	 */
	public void save(FileWriter file) throws IOException {
		for(int i=0; i<this.filas; i++) {
			for(int j=0; j<this.columnas; j++) {
				if(this.superficie[i][j] != null) {
					file.write(i + " " + j + " ");
					this.superficie[i][j].save(file);
					file.write(System.getProperty("line.separator"));
				}
			}
		}
	}
}
