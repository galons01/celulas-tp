package tp.pr1.logica;

public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	
	//constructora de la superficie
	public Superficie(int nf, int nc){
		this.filas=nf;
		this.columnas=nc;
		this.superficie = new Celula[nf][nc];
	}
	
	public boolean insertarCelula(int fila, int columna){
		boolean vacio = this.superficie[fila][columna]==null;
		if(vacio){
			this.superficie[fila][columna] = new Celula();
		}
		return vacio;
	}
	
}