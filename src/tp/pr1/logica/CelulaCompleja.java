package tp.pr1.logica;

public class CelulaCompleja extends Celula {
	
	private final static int MAX_COMER = 3;
	private int celsComidas;
	
	public CelulaCompleja() {
		this.comestible = false;
		this.celsComidas = 0;
	}
	
	public boolean esComestible() {
		return this.comestible;
	}
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie){
		Casilla pos;
		boolean hayCelula;
		
		if(!superficie.posLibre(f,c)) {
			/*Busca una posición válida a la que moverse*/
			pos = superficie.posAleatoria();
			
			/*Si está libre o es comestible*/
			hayCelula = !superficie.posLibre(pos.getFila(), pos.getColumna());
			if(!hayCelula || superficie.esComestible(pos.getFila(), pos.getColumna())){
				superficie.mover(f, c, pos.getFila(), pos.getColumna());
				System.out.print("Celula compleja en (" + f + "," + c + ") " + 
						"se mueve a " + pos);				
				/*Si había otra célula*/
				if(hayCelula) {
					this.celsComidas++;
					System.out.println("--COME--");
					/*Si ha comido demasiado, explota*/
					if(this.celsComidas >= CelulaCompleja.MAX_COMER) {
						superficie.eliminar(pos.getFila(), pos.getColumna());
						System.out.println("Explota la celula compleja en " + pos);
					}
				}
				else System.out.println("--NO COME--");
				return pos;
			}
					
		}
		return null;
	}

	public String toString() {
		return "( " + this.celsComidas + " )";
	}
}
