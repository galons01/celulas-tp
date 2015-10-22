package tp.pr1.logica;

public class Mundo {
	
	private final static int N_FILAS=3;
	private final static int N_COLUMNAS=4;
	
	private final static int DEF_CELULAS=6;
	
	private Superficie superficie = new Superficie(N_FILAS,N_COLUMNAS);
	
	public void evoluciona() {
		
	}
	
	public boolean crearCelula(int fila, int columna) {
		return this.superficie.insertarCelula(fila, columna);
	}
	
	int numAleatorio(int min, int max) {
		if(min<max){
			return (int)Math.random()*(max-min+1)+min;	
		}
		else {
			return 0;
		}
	}
}
