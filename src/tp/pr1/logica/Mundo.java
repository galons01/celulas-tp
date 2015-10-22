package tp.pr1.logica;

public class Mundo {
	
	private final static int N_FILAS=3;
	private final static int N_COLUMNAS=4;
	
	private final static int DEF_CELULAS=6;
	
	private Superficie superficie = new Superficie(N_FILAS,N_COLUMNAS);
	
	public void evoluciona() {
		//Evolucionar aquí <---
	}
	
	//Crea una nueva célula en el mundo
	public boolean crearCelula(int fila, int columna) {
		return this.superficie.insertarCelula(new Celula(), fila, columna);
	}
	
	//Mueve una célula si la posición está disponible
	public boolean moverCelula(int f1, int c1, int f2, int c2) {
			if(this.superficie.posDisponible(f2,c2)) {			
			Celula cel = this.superficie.getCelula(f1, c1);
			this.superficie.insertarCelula(cel, f2, c2);
			this.superficie.eliminarCelula(c1, c1);
			return true;
		}
		else
			return false;
	}
	
	//Devuelve un entero aleatorio entre min y max
	int numAleatorio(int min, int max) {
		if(min<max)
			return (int)Math.random()*(max-min+1)+min;	
		else
			return 0;
	}
}
