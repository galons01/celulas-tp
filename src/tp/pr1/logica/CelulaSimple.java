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
		//Si la posiciÃ³n en cuestiÃ³n tiene una cÃ©lula
		if(!superficie.posLibre(f,c)) {
			/*si a la celula le quedan movimientos*/
			if(this.puedeMoverse()){
				
				
				//Nos aseguramos de que no nos salimos
				int leftC = Math.max(c-1,0),	//Columna izquierda Ã³ 0
					rightC = Math.min(c+1, superficie.getColumnas()-1); //Columna derecha o nColumnas
				int topF = Math.max(f-1,0),	//Fila superior Ã³ 0
					bottomF = Math.min(f+1, superficie.getFilas()-1);	//Fila inferior o nFilas
				
				Casilla[] libres = new Casilla[8];	//Array de posiciones libres
				int l = 0;	//Contador de libres[]
				
				//Recorre las posiciones colindantes
				for(int i = topF; i<=bottomF; i++) {
					for(int j = leftC; j<=rightC; j++) {
						//Si la posiciÃ³n estÃ¡ libre -> aÃ±adir al array
						if(superficie.posLibre(i,j)){
							libres[l] = new Casilla(i,j);
							l++;
						}
					}
				}
				//Si hay al menos 1 posiciÃ³n libre
				if(l > 0) {					
					/*Elegimos una posiciÃ³n aleatoria entre las libres y 
					la copiamos a la variable de salida */
					l = Mundo.numAleatorio(0,l-1);
					int col=libres[l].getColumna();
					int fil=libres[l].getFila();
					// si puede reproducirse, se reproduce
					if(this.puedeReprod()){
						superficie.insertarCelula(new CelulaSimple(), fil, col);
						this.reproducir();
						System.out.println("Nace nueva cÃ©lula en (" + fil + "," + col + ") " + 
								"cuyo padre ha sido (" + f + "," + c + ") ");
						//un else o if no es necesario dado que siempre puede moverse y por lo tanto reproducirse.
					}
					//si no puede reproducirse(porque no le toca), se mueve
					else{
						superficie.moverCelula(f,c,fil,col);
						System.out.println("Movimiento de (" + f + "," + c + ") " + 
								"a (" + fil + "," + col + ") ");
					}
					return libres[l];
				}
				//si no hay huecos libres pero debe moverse
				else{
					this.estarQuieta();
				}

			}
			else{
				superficie.eliminarCelula(f, c);
				System.out.println("Muere la cÃ©lula de la casilla (" + f + "," + c + ") " + 
						"por inactividad");
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
