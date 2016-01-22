package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.IndicesFueraDeRango;
import tp.pr1.excepciones.PalabraIncorrecta;

public abstract class Mundo {
	
	protected final int N_FILAS;
	protected final int N_COLUMNAS;
	
	protected Superficie superficie;
	
	/**
	 * Construye un mundo con una superficie de 5 filas y 4 columnas.
	 * Constructor por defecto.
	 */
	public Mundo() {
		this.N_FILAS = 5;
		this.N_COLUMNAS = 4;
		this.superficie = new Superficie(N_FILAS,N_COLUMNAS);
	}
	
	/**
	 * Construye un mundo con una superficie.
	 * @param f Número de filas.
	 * @param c Número de columnas.
	 */
	public Mundo(int f, int c) {
		this.N_FILAS = f;
		this.N_COLUMNAS = c;
		this.superficie = new Superficie(N_FILAS,N_COLUMNAS);
	}
	
	
	/**
	 * Inicializa el mundo con células aleatorias.
	 * @throws ErrorDeInicializacion Si el mundo no es válido.
	 */
	abstract public void iniciar() throws ErrorDeInicializacion;
	
	/**
	 * Crea una celula en el mundo (para usar desde fuera)
	 * @param f Fila
	 * @param c Columna
	 * @return Devuelve si ce creó la celula
	 * @throws IndicesFueraDeRango Si la posición está fuera del mundo
	 * @throws PalabraIncorrecta Si el tipo de célula es deconocido para el mundo
	 */
	abstract public boolean crearCelula(int f, int c) throws IndicesFueraDeRango, PalabraIncorrecta;
	
	/**
	 * Elimina una celula del mundo (para usar desde fuera)
	 * @param f Fila
	 * @param c Columna
	 * @return Devuelve si se eliminó la celula
	 * @throws IndicesFueraDeRango Si la posición está fuera del mundo
	 */
	public boolean eliminarCelula(int f, int c) throws IndicesFueraDeRango {
		if(!this.superficie.posLibre(f, c)) {
			this.superficie.eliminar(f, c);
			return true;
		}
		else return false;
	}

	
	/**
	 * Devuelve un número entero positivo pseudo-aleatorio 
	 * entre a y b (ambos incluidos).
	 * @param a Cota (debe ser un número natural o cero).
	 * @param b Cota (debe ser un número natural o cero).
	 * @return Número pseudo-aleatorio. 
	 */
	public static int numAleatorio(int a, int b) {
		if(a>b) {	/*Swap numbers*/
			a = a + b; //a = a + b
			b = a - b; //b(new) = (a+b) - b = a(old)
			a = a - b; //a(new) = (a+b) - b(new) = (a+b) - ((a+b)-b) = b(old)
		}
		return (int)(Math.random()*(b-a+1)+a);	
	}
	
	
	/**
	 * Recorre la superficie del mundo en busca de células.
	 * @return Devuelve una ListaCasillas con las posiciones ocupadas por células.
	 */
	private ListaCasillas inspecSuperficie() {
		int N = this.superficie.nElems();	/*Número total de células en la superficie*/
		ListaCasillas ocupadas = new ListaCasillas(N);
		int n = 0;				/*Contador de número de células*/
		int j;					/*Contador bucle while*/
		int F = this.superficie.getFilas();	/*Número de  filas*/
		int C = this.superficie.getColumnas();	/*Número de columnas*/
		
		try {
			for(int i = 0; i<F; i++) {
				j = 0;
				while(j<C && n<N) {
					/*Si encuentra una posición ocupada (una célula)*/
					if(!this.superficie.posLibre(i,j)) {
						ocupadas.add(new Casilla(i,j), n);
						n++;
					}
					j++;
				}
			}
		} catch (IndicesFueraDeRango e) {
			//Do nothing. Secure configuration.
		}
		return ocupadas;
	}
	
	/**
	 * Limpia la superficie del mundo, dejándolo sin células
	 */
	public void vaciar(){
		this.superficie.vaciar();
	}
	
	/**
	 * Da un paso en la evolución del mundo para todas las células.
	 */
	public void evoluciona() {
		/*
		 Foto del número de células en la superficie. Podría ser modificado 
		 durante el bucle dando lugar a errores.
		*/
		ListaCasillas ocupadas;
		int x;
		Casilla nuevaPos;
		
		/*Busca las células que hay en el mundo*/
		ocupadas = inspecSuperficie();
		
		/*Y aplica la lógica para cada una de ellas*/
		for(int i=0; i<ocupadas.len(); i++) {
			try {
				nuevaPos = this.superficie.ejecutaMovimiento(ocupadas.get(i));
				/*Si se ha movido*/
				if(nuevaPos!=null) {
					/*Asegurándose de que la posición a la que se ha movido
					  no está en la lista para que no se mueva dos veces*/
					if(nuevaPos.greater(ocupadas.get(i))) {
						x = ocupadas.buscar(nuevaPos);
						if(x>=0)
							ocupadas.eliminarCasilla(x);
					}
				}
			} catch (IndicesFueraDeRango e) {
				/*No hace nada. Sigue con el bucle.*/
			}
		}
	}

	/**
	 * Representa en un String la superficie.
	 */
	public String toString() {
		return this.superficie.toString();
	}
	
	/**
	 * Carga un mundo del archivo.
	 * @param archivo Archivo desde el que se carga el mundo.
	 * @throws ErrorDeCarga Si el archivo está corrupto.
	 */
	public abstract void cargar(Scanner archivo) throws ErrorDeCarga;
	
	/**
	 * Guarda un mundo en el archivo.
	 * @param file Archivo en el que se guarda el mundo.
	 * @throws ErrorDeCarga Si ocurre un error con el archivo.
	 */
	public abstract void guardar(FileWriter file) throws IOException;
}

