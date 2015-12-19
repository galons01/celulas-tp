package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Celula {
	
	protected boolean comestible;
	
	/**
	 * Ejecuta la lógica de una célula en una superficie
	 * @param f Fila en la que se ebcuentra la célula dentro de la superficie
	 * @param c Columna en la que se ebcuentra la célula dentro de la superficie
	 * @param superficie Superficie en la que se ebcuentra la célula
	 * @return Casilla a la que se ha movido o null.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * Determina si una célula es comestible o no.
	 * @return true si es comestible, false en caso contrario
	 */
	public abstract boolean esComestible();
	
	/**
	 * @return String que representa una célula
	 */
	public abstract String toString();
	
	/**
	 * Guarda la celula en un archivo dado.
	 * @param file Archivo al que va la célula
	 * @throws IOException
	 */
	public abstract void save(FileWriter file) throws IOException;
}
