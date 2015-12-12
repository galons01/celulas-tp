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
		Casilla pos = null;
		/*Si le quedan movimientos */
		if(this.pasosMuerte > 0) {
			pos = new Casilla(f,c);
			
			/*Si le toca reproducirse */
			if(this.pasosReprod <= 0) {
				/*Se mueve la madre y nace la hija*/
				if(CelulaSimple.moverCelula(pos,superficie) != null) {
					superficie.insertarCelula(new CelulaSimple(),f,c);
					this.pasosReprod = CelulaSimple.PASOS_REPRODUCCION;
					System.out.println("Nace nueva célula en (" + f + "," + c + ") " + 
							"cuyo padre ha sido (" + pos.getFila() + "," + pos.getColumna() + ") ");
				}
				/*Si no se puede dividir, muere*/
				else {
					superficie.eliminarCelula(f,c);
					System.out.println("Muere la célula de la casilla (" + f + "," + c + ") " + 
							"por no poder reproducirse");
				}
				
			}
			/*Si no le tocaba reproducirse, intenta moverse */
			else if(CelulaSimple.moverCelula(pos, superficie) != null) {
				this.pasosReprod--;
				System.out.println("Movimiento de (" + f + "," + c + ") " + 
						"a (" + pos.getFila() + "," + pos.getColumna() + ") ");
			}
			/*Si no se reproduce ni se mueve*/
			else {
				this.pasosMuerte--;
			}
		}
		/*Si no le quedan pasos se muere*/
		else {
			superficie.eliminarCelula(f,c);
			System.out.println("Muere la célula de la casilla (" + f + "," + c + ") " + 
					"por inactividad");
		}
		return pos;
	}
	
	
	/**
	 * INTENTA mover una célula
	 * @param pos Posición de entrada y salida, null si no se ha movido
	 * @return Devuelve el parámetro pos también.
	 */
	private static Casilla moverCelula(Casilla pos, Superficie superficie) {
		/*Copiamos la posicion original*/
		int f = pos.getFila();
		int c = pos.getColumna();

		/*Busca una casilla a la que moverse*/
		Casilla nuevaPos = CelulaSimple.inspecAlrededores(f,c,superficie);
		/*Si se puede mover porque hay un hueco*/
		if(nuevaPos!=null) {
			pos.set(nuevaPos); /*Para devolverlo por el parámetro*/
			superficie.moverCelula(f, c, pos.getFila(), pos.getColumna());
		}
		return nuevaPos;
	}
	
	
	/**
	 * Revisa los alrededores de una célula en busca de una posición 
	 * libre aleatoria en torno a la posición indicada.
	 * @param f Fila
	 * @param c Columna
	 * @param superficie
	 * @return Posición libre aleatoria, null si no hay ninguna
	 */
	private static Casilla inspecAlrededores(int f, int c, Superficie superficie) {
		//Si la posición en cuestión tiene una célula
		if(!superficie.posLibre(f,c)) {
			//Nos aseguramos de que no nos salimos
			int leftC = Math.max(c-1,0),	//Columna izquierda ó 0
				rightC = Math.min(c+1, superficie.getColumnas()-1); //Columna derecha o nColumnas
			int topF = Math.max(f-1,0),	//Fila superior ó 0
				bottomF = Math.min(f+1, superficie.getFilas()-1);	//Fila inferior o nFilas
			
			Casilla[] libres = new Casilla[8];	//Array de posiciones libres
			int l = 0;							//Contador de libres[]
			
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
			/*Si hay al menos 1 posición libre
			  devuelve una posición aleatoria libre*/
			if(l > 0)
				return libres[Mundo.numAleatorio(0,l-1)];
		}
		return null;
	}
	

	public boolean esComestible() {
		return this.comestible;
	}
	
	public String toString() {
		return "[" + this.pasosMuerte + "-" + this.pasosReprod + "]";
	}
}
