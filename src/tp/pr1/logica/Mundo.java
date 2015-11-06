package tp.pr1.logica;
import tp.pr1.logica.Casilla;

public class Mundo {
	
	private final static int N_FILAS = 3;
	private final static int N_COLUMNAS = 4;
	private final static int DEF_CELULAS = N_FILAS*N_COLUMNAS/4; /*25% de células aleatorias*/
	
	private Superficie superficie = new Superficie(N_FILAS,N_COLUMNAS);

	
	//Crea una nueva célula en el mundo
	public boolean crearCelula(int fila, int columna) {
		return this.superficie.insertarCelula(new Celula(), fila, columna);
	}
	//Elimina una célula del mundo (para usar desde fuera)
	public void eliminarCelula(int f, int c) {
		this.superficie.eliminarCelula(f, c);
	}

	
	//Devuelve un entero aleatorio entre min y max
	public static int numAleatorio(int min, int max) {
		//if(min<max)
			return (int)(Math.random()*(max-min+1)+min);	
		/*else
			return 0;*/
	}

	//Crea DEF_CELULAS células en posiciones aleatorias
	public void iniciar() {
		int i = 0;
		int f, c;
		
		//this.superfice.vaciar();
		while(i<Mundo.DEF_CELULAS) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			if(this.crearCelula(f,c))
				i++;
		}
	}
	
	/*Revisa los alrededores de una célula en busca de
	una posición libre aleatoria*/
	private boolean inspecAlrededores(Casilla pos) {
		//Si la posición en cuestión tiene una célula
		if(!this.superficie.posLibre(pos.getFila(),pos.getColumna())) {
			
			//Nos aseguramos de que no nos salimos
			int leftC = Math.max(pos.getColumna()-1,0),	//Columna izquierda ó 0
				rightC = Math.min(pos.getColumna()+1, this.superficie.getColumnas()-1); //Columna derecha o nColumnas
			int topF = Math.max(pos.getFila()-1,0),	//Fila superior ó 0
				bottomF = Math.min(pos.getFila()+1, this.superficie.getFilas()-1);	//Fila inferior o nFilas
			
			Casilla[] libres = new Casilla[8];	//Array de posiciones libres
			int l = 0;	//Contador de libres[]
			boolean encontradoHueco;
			
			//Recorre los alrededores
			for(int i = topF; i<=bottomF; i++) {
				for(int j = leftC; j<=rightC; j++) {
					//Si la posición está libre -> añadir al array
					if(this.superficie.posLibre(i,j)){
						libres[l] = new Casilla(i,j);
						l++;
					}
				}
			}
			//Si hay al menos 1 posición libre
			encontradoHueco = l > 0;
			if(encontradoHueco) {
				l--;
				l = Mundo.numAleatorio(0,l);
				pos.setColumna(libres[l].getColumna());
				pos.setFila(libres[l].getFila());
			}/*Esto ^^^ probablemente vaya mejor en otra 
				función a parte. Ahí lo dejo de momento*/
			
			return encontradoHueco;	//True si había un hueco alrededor
		}
		else
			return false;	//No había célula en la posición indicada
	}
	
	/*
	 INTENTA mover una célula
	*/
	private boolean moverCelula(Casilla pos) {
		
		/*Copiamos la posicion original*/
		int f = pos.getFila();
		int c = pos.getColumna();
		/*Si se puede mover*/
		if(this.inspecAlrededores(pos)) {
			/*Mueve la célula y resta paso reproducción*/
			this.superficie.moverCelula(f, c, pos.getFila(), pos.getColumna());
			this.superficie.darPaso(f,c);
			return true;
		}
		/*Si no se puede mover*/
		else {
			/*Resta un paso*/
			this.superficie.estarQuieta(f,c);
			return false;
		}
	}
	
	/*
	 Aplica la lógica para una célula.
	 */
	private void paso(int f, int c) {
		
		/*Si le quedan movimientos */
		if(this.superficie.puedeMoverse(f,c)) {
			Casilla pos = new Casilla(f,c);
			
			/*Si le toca reproducirse */
			if(this.superficie.puedeReprod(f,c)) {
				
				if(this.moverCelula(pos)) {
					this.crearCelula(f,c);
					System.out.println("Nace nueva célula en (" + f + "," + c + ") " + 
							"cuyo padre ha sido (" + pos.getFila() + "," + pos.getColumna() + ") ");
				}
				/*Si no se puede dividir, muere*/
				else {
					this.superficie.eliminarCelula(f, c);
					System.out.println("Muere la célula de la casilla (" + f + "," + c + ") " + 
							"por no poder reproducirse");
				}
				
			}
			/*Si no le tocaba reproducirse, intenta moverse */
			else if(this.moverCelula(pos)) {
				System.out.println("Movimiento de (" + f + "," + c + ") " + 
						"a (" + pos.getFila() + "," + pos.getColumna() + ") ");
			}
		}
		/*Si no le quedan pasos se muere*/
		else {
			this.superficie.eliminarCelula(f, c);
			System.out.println("Muere la célula de la casilla (" + f + "," + c + ") " + 
					"por inactividad");
		}
	}
	
	
	/*
	 Recorre la superficie en busca de células.
	 Devuelve un array con las posiciones ocupadas.
	*/
	private int inspecSuperficie(Casilla[] ocupadas) {
		int n = 0;	/*Número de células*/
		int j;		/*Contador bucle while*/
		for(int i = 0; i<this.superficie.getFilas(); i++) {
			j = 0;
			while(j<this.superficie.getColumnas() && n<this.superficie.nCelulas()) {
				if(!this.superficie.posLibre(i, j)) {
					ocupadas[n] = new Casilla(i,j);
					n++;
				}
				j++;
			}
		}
		return n;
	}
	
	public void evoluciona() {
		Casilla[] ocupadas = new Casilla[this.superficie.getColumnas()*this.superficie.getFilas()];
		int f, c;
		int nLibres = inspecSuperficie(ocupadas);
		
		for(int a=0; a<nLibres; a++) {
			f = ocupadas[a].getFila();
			c = ocupadas[a].getColumna();
			paso(f,c);
		}
		System.out.println(this);
	}
	
	public String toString() {
		StringBuilder mostrar = new StringBuilder();
		int pasosReprod, pasosMuerte;
		for(int i = 0; i< this.superficie.getFilas(); i++) {
			for(int j = 0; j< this.superficie.getColumnas(); j++) {
				if(this.superficie.posLibre(i,j)) {
					mostrar.append("  -  ");
				}
				else {
					pasosReprod = this.superficie.getPasosReprod(i,j);
					pasosMuerte = this.superficie.getPasosMuerte(i,j);
					mostrar.append("[" + pasosMuerte + "," + pasosReprod +"]");
				}
				mostrar.append(" ");
			}
			mostrar.append("\n");
		}
		return mostrar.toString();
	}
}
