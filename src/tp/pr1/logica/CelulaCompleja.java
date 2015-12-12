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
		Casilla pos = null;
		boolean celulaComestible;
		
		if(!superficie.posLibre(f,c)) {
			/*Busca una posición válida a la que moverse*/
			pos = CelulaCompleja.inspecSuperficie(f, c, superficie);
			/*Si hay casillas disponibles*/
			if(pos!=null) {
				/*Si va a comer otra célula*/
				celulaComestible = !superficie.posLibre(pos.getFila(), pos.getColumna());
				
				superficie.moverCelula(f, c, pos.getFila(), pos.getColumna());
				System.out.print("Celula compleja en (" + f + "," + c + ") " + 
						"se mueve a (" + pos.getFila() + "," + pos.getColumna() + ")");
				
				if(celulaComestible) {
					this.celsComidas++;
					System.out.println("--COME--");
					/*Si ha comido demasiado, explota*/
					if(this.celsComidas >= CelulaCompleja.MAX_COMER) {
						superficie.eliminarCelula(pos.getFila(), pos.getColumna());
						System.out.println("Explota la celula compleja en "
								+ "(" + pos.getFila() + "," + pos.getColumna() + ") ");
					}
				}
				else
					System.out.println("--NO COME--");
			}
		}
		return pos;
	}
	
	
	/**
	 * Revisa la superficie en busca de una posición válida aleatoria
	 * para una célula compleja.
	 * @param f Fila
	 * @param c Columna
	 * @param superficie
	 * @return Posición válida aleatoria, null si no hay ninguna
	 */
	private static Casilla inspecSuperficie(int f, int c, Superficie superficie) {
		//Si la posición en cuestión tiene una célula
		if(!superficie.posLibre(f,c)) {
			int nC = superficie.getColumnas();
			int nF = superficie.getFilas();

			Casilla[] disponibles = new Casilla[nF*nC];	//Array de posiciones válidas
			int l = 0;	//Contador de disponibles[]
			
			for (int i=0; i<nF; i++){
				for (int j=0; j<nC; j++){
					if(superficie.posLibre(i,j) || superficie.esComestible(i,j)){
						disponibles[l]= new Casilla(i,j);
						l++;
					}
				}
			}
			
			/*Si hay al menos 1 posición válida
			  devuelve una posicion aleatoria válida*/
			if(l > 0) {
				return disponibles[Mundo.numAleatorio(0,l-1)];
			}
		}
		return null;
	}
	

	public String toString() {
		return "( " + this.celsComidas + " )";
	}
}
