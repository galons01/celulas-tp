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
		//si hay casillas disponibles
		if(l>0) {
			/*Elegimos una posiciÃ³n aleatoria entre las libres y 
			la copiamos a la variable de salida */
			l = Mundo.numAleatorio(0,l-1);
			int fil=disponibles[l].getFila();//@german ¿es mas claro asi o con disponibles que piensas? es mas bonito con fil y col en mi opinion pero puede ser menos legible...
			int col=disponibles[l].getColumna();
			boolean zampaCelulas=false;			//nueva boleana para el mensaje de la celula compleja que muestra si ha comido o no
			//si la casilla esta ocupada por una celula, la compleja come a la simple
			if(!superficie.posLibre(fil,col)){
				zampaCelulas=true;
				this.comer();
				superficie.eliminarCelula(disponibles[l].getFila(), disponibles[l].getColumna());
				}
			//se mueve
				superficie.moverCelula(f, c, disponibles[l].getFila(), disponibles[l].getColumna());
				if(zampaCelulas){
					System.out.println("Celula compleja en (" + f + "," + c + ") " + 
						"se mueve a (" + fil + "," + col + ") --COME--");
					//si la celula compleja come hasta reventar... revienta (superando el limite de las celulas maximas comidas)
					if(!this.puedeComer()){
						superficie.eliminarCelula(fil,col);
						System.out.println("Explota la celula compleja en (" + fil + "," + col + ") ");
					}
				}
				else
					System.out.println("Celula compleja en (" + f + "," + c + ") " + 
							"se mueve a (" + fil + "," + col + ") --NO COME--");
				return disponibles[l];
			}
			// si no hay casillas disponibles para moverse no afecta a la celula compleja, es decir, no hace falta un else
		else{
			return null; // si no hay posible movimiento, no se mueve
		}
	}
	
	public void comer() {
		if(this.celsComidas > 0)
			this.celsComidas--;
	}
	
	public boolean puedeComer() {
		return this.celsComidas > 0;
	}
}
