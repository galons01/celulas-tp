package tp.pr1.logica;

public class CelulaSimple extends Celula {
	private static final short MAX_PASOS_SIN_MOVER = 2;
	private static final short PASOS_REPRODUCCION = 4;

	private short pasosReprod;	//Pasos que le quedan para dividirse (pasos movidos)
	private short pasosMuerte;	//Pasos que le quedan para morir (pasos sin moverse)
	
	public CelulaSimple() {
		this.pasosReprod = CelulaSimple.PASOS_REPRODUCCION;
		this.pasosMuerte = CelulaSimple.MAX_PASOS_SIN_MOVER;
		this.comestible = true;
	}
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie){
		//Si la posición en cuestión tiene una célula
		if(!superficie.posLibre(f,c)) {
			
			//Nos aseguramos de que no nos salimos
			int leftC = Math.max(c-1,0),	//Columna izquierda ó 0
				rightC = Math.min(c+1, superficie.getColumnas()-1); //Columna derecha o nColumnas
			int topF = Math.max(f-1,0),	//Fila superior ó 0
				bottomF = Math.min(f+1, superficie.getFilas()-1);	//Fila inferior o nFilas
			
			Casilla[] libres = new Casilla[8];	//Array de posiciones libres
			int l = 0;	//Contador de libres[]
			
			//Recorre las posiciones colindantes
			for(int i = topF; i<=bottomF; i++) {
				for(int j = leftC; j<=rightC; j++) {
					//Si la posición está libre -> añadir al array
					if(superficie.posLibre(i,j)){
						libres[l] = new Casilla(i,j);
						l++;
					}
				}
			}
			//Si hay al menos 1 posición libre
			if(l > 0) {
				/*Elegimos una posición aleatoria entre las libres y 
				la copiamos a la variable de salida */
				l = Mundo.numAleatorio(0,l-1);
				return libres[l];
			}
		}
		return null;
	}
	
	public boolean esComestible() {
		return this.comestible;
	}
	
	public short getPasosReprod() { 
		return this.pasosReprod;
	}

	public short getPasosMuerte() {
		return this.pasosMuerte;
	}
	
	public boolean puedeReprod() { 
		return this.pasosReprod == 0;
	}

	public boolean puedeMoverse() {
		return this.pasosMuerte > 0;
	}
	
	/*Resta un paso para la reproduccion de la célula */
	public void darPaso() {
		if(this.pasosReprod > 0)
			this.pasosReprod--;
	}
	
	/*Resta un paso para la muerte de la célula */
	public void estarQuieta() {
		if(this.pasosMuerte > 0)
			this.pasosMuerte--;
	}
	
	/*Reproduce una célula si puede*/
	public boolean reproducir() {
		if(this.pasosReprod == 0) {
			this.pasosReprod = CelulaSimple.PASOS_REPRODUCCION;
			return true;
		}
		return false;
	}
}
