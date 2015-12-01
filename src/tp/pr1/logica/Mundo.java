package tp.pr1.logica;

public class Mundo {
	
	private final static int N_FILAS = 3;
	private final static int N_COLUMNAS = 4;
	private final static int DEF_CELULAS = N_FILAS*N_COLUMNAS/3; /*33% de células aleatorias*/
	private final static int DEF_CELULAS_COMPLEJAS = DEF_CELULAS/2; /*50% de células complejas*/
	
	private Superficie superficie = new Superficie(N_FILAS,N_COLUMNAS);
	
	
	//Crea una nueva célula simple en el mundo
	public boolean crearCelulaSimple(int f, int c) {
		if(this.superficie.posValida(f, c))
			return this.superficie.insertarCelula(new CelulaSimple(), f, c);
		else
			return false;
	}
		
	//Crea una nueva célula compleja en el mundo
	public boolean crearCelulaCompleja(int f, int c) {
		if(this.superficie.posValida(f, c))
			return this.superficie.insertarCelula(new CelulaCompleja(), f, c);
		else
			return false;
	}
	
	//Elimina una célula del mundo (para usar desde fuera)
	public boolean eliminarCelula(int f, int c) {
		if(this.superficie.posValida(f, c)) {
			this.superficie.eliminarCelula(f, c);
			return true;
		}
		else
			return false;
	}

	
	//Devuelve un entero pseudo-aleatorio entre min y max
	public static int numAleatorio(int min, int max) {
		if(min>max) {	/*Swap numbers*/
			min = min + max; //Total = max + min
			max = min - max; //max = total - max = min(old)
			min = min - max; //min = total - max = total - min = max(old)
		}
		return (int)(Math.random()*(max-min+1)+min);	
	}

	//Crea DEF_CELULAS células en posiciones aleatorias
	public void iniciar() {
		int i = 0;
		int f, c;
		
		this.superficie.vaciar();
		
		/*Crea las células complejas*/
		while(i<Mundo.DEF_CELULAS_COMPLEJAS) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			if(this.crearCelulaCompleja(f,c))
				i++;
		}
		/*Crea las células simples*/
		i = 0;
		while(i<(Mundo.DEF_CELULAS-Mundo.DEF_CELULAS_COMPLEJAS)) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			if(this.crearCelulaSimple(f,c))
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
			
			//Recorre las posiciones colindantes
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
				/*Elegimos una posición aleatoria entre las libres y 
				la copiamos a la variable de salida */
				l = Mundo.numAleatorio(0,l-1);
				libres[l].copiar(pos);
			}
			
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

		/*Si se puede mover porque hay un hueco*/
		if(this.inspecAlrededores(pos)) {
			/*Mueve la célula y resta paso reproducción*/
			this.superficie.moverCelula(f, c, pos.getFila(), pos.getColumna());
			this.superficie.darPaso(pos.getFila(),pos.getColumna());
			return true;
		}
		/*Si no se puede mover*/
		else {
			/*Resta un paso para morir*/
			this.superficie.estarQuieta(f,c);
			return false;
		}
	}
	
	/*
	 Aplica la lógica del mundo para una célula.
	 */
	private void paso(int f, int c) {
		
		/*Si le quedan movimientos */
		if(this.superficie.puedeMoverse(f,c)) {
			Casilla pos = new Casilla(f,c);
			
			/*Si le toca reproducirse */
			if(this.superficie.puedeReprod(f,c)) {
				/*Se mueve la madre y nace la hija*/
				if(this.moverCelula(pos)) {
					this.crearCelula(f,c);
					this.superficie.reproducir(pos.getFila(),pos.getColumna());
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
	private void inspecSuperficie(Casilla[] ocupadas) {
		int N = this.superficie.nCelulas();	/*Número total de células en la superficie*/
		int n = 0;				/*Contador de número de células*/
		int j;					/*Contador bucle while*/
		int F = this.superficie.getFilas();	/*Número de  filas*/
		int C = this.superficie.getColumnas();	/*Número de columnas*/

		for(int i = 0; i<F; i++) {
			j = 0;
			while(j<C && n<N) {
				/*Si encuentra una posición ocupada (una célula)*/
				if(!this.superficie.posLibre(i,j)) {
					ocupadas[n] = new Casilla(i,j);
					n++;
				}
				j++;
			}
		}
	}
	
	/*Función puente para vaciar la superficie*/
	public void vaciar(){
		this.superficie.vaciar();
	}
	
	public void evoluciona() {
		/*
		 Foto del número de células en la superficie. Podría ser modificado 
		 durante el bucle dando lugar a errores.
		*/
		int n = this.superficie.nCelulas();
		Casilla[] ocupadas = new Casilla[n];
		int f, c;
		
		/*Busca las células que hay en el mundo*/
		inspecSuperficie(ocupadas);
		/*Y aplica la lógica para cada una de ellas*/
		for(int i=0; i<n; i++) {
			f = ocupadas[i].getFila();
			c = ocupadas[i].getColumna();
			paso(f,c);
		}
	}
	
	/*Devuelve un string del mundo*/
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
					mostrar.append("[" + pasosMuerte + "-" + pasosReprod +"]");
				}
				mostrar.append(" ");
			}
			mostrar.append("\n");
		}
		return mostrar.toString();
	}
}

