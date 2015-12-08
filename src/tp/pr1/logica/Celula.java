package tp.pr1.logica;

public abstract class Celula {
	
	protected boolean comestible;
	
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	public abstract boolean esComestible();
}
