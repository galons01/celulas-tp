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
		
		return (new Casilla(0,0));
	}
	
	public void comer() {
		if(this.celsComidas > 0)
			this.celsComidas--;
	}
	
	public boolean puedeComer() {
		return this.celsComidas > 0;
	}
}
