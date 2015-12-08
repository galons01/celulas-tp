package tp.pr1.logica;

public class CelulaCompleja extends Celula {
	
	private final static int MAX_COMER = 3;
	private int celsComidas;
	
	public CelulaCompleja() {
		this.comestible = false;
		this.celsComidas = CelulaCompleja.MAX_COMER;
	}
	
	public boolean esComestible() {
		return this.comestible;
	}
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie){
		int nC = superficie.getColumnas();
		int nF = superficie.getFilas();
		
		Casilla[] disponibles = new Casilla[nF*nC];	//Array de posiciones libres
		int l = 0;	//Contador de disponibles[]
		
		for (int i=0; i<nF; i++){
			for (int j=0; j<nC; j++){
				if(superficie.posLibre(i,j) || superficie.esComestible(i,j)){
					disponibles[l]= new Casilla(i,j);
					l++;
				}
			}
		}
		
		if(l>0) {
			/*Elegimos una posición aleatoria entre las libres y 
			la copiamos a la variable de salida */
			l = Mundo.numAleatorio(0,l-1);
			superficie.eliminarCelula(disponibles[l].getFila(), disponibles[l].getColumna());
			superficie.moverCelula(f, c, disponibles[l].getFila(), disponibles[l].getColumna());
			}
		return disponibles[l];
	}
	
	public void comer() {
		if(this.celsComidas > 0)
			this.celsComidas--;
	}
	
	public boolean puedeComer() {
		return this.celsComidas > 0;
	}
}
