package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.IndicesFueraDeRango;

public interface Celula {
	
	/**
	 * Ejecuta la lógica de una célula en una superficie
	 * @param f Fila en la que se ebcuentra la célula dentro de la superficie
	 * @param c Columna en la que se ebcuentra la célula dentro de la superficie
	 * @param superficie Superficie en la que se ebcuentra la célula
	 * @return Casilla a la que se ha movido o null.
	 * @throws IndicesFueraDeRango Si la posicion está fuera de la superficie dada.
	 */
	public Casilla ejecutaMovimiento (int f, int c, Superficie superficie)
			throws IndicesFueraDeRango;
	
	/**
	 * Determina si una célula es comestible o no.
	 * @return true si es comestible, false en caso contrario
	 */
	public boolean esComestible();
	
	/**
	 * @return String que representa una célula
	 */
	public String toString();
	
	/**
	 * Guarda la celula en un archivo dado.
	 * @param file Archivo al que va la célula
	 * @throws IOException
	 */
	public abstract void save(FileWriter file) throws IOException;
	
	/**
	 * Carga la celula de un archivo dado.
	 * @param file Archivo al que va la célula
	 * @throws IOException Si se produce un error de carga
	 * @throws ErrorDeCarga Si la célula leída es errónea.
	 */
	public abstract void cargar(Scanner file) throws ErrorDeCarga;
}
