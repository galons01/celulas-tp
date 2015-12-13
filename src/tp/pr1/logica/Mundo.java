package tp.pr1.logica;

public class Mundo {
	
	private final static int N_FILAS = 3;
	private final static int N_COLUMNAS = 4;
	private final static int DEF_CELULAS = N_FILAS*N_COLUMNAS/3; /*33% de células aleatorias*/
	private final static int DEF_CELULAS_COMPLEJAS = DEF_CELULAS/2; /*50% de células complejas*/
	
	private Superficie superficie = new Superficie(N_FILAS,N_COLUMNAS);
	private boolean simulacionTerminada = true;
	
	
	/**
	 * Indica si la simulación ha terminado de ejecutarse.
	 * @return Booleano inidcando true si la simulación está activa.
	 */
	public boolean simulacionTerminada() {
		return this.simulacionTerminada;
	}
	
	
	/**
	 * Indica al mundo que debe terminar la simulación.
	 */
	public void terminarSimulacion() {
		this.simulacionTerminada = false;
	}
	
	
	//Crea una nueva célula simple en el mundo
	public boolean crearCelulaSimple(int f, int c) {
		if(this.superficie.posValida(f,c) && this.superficie.posLibre(f,c)) {
			this.superficie.insertarCelula(new CelulaSimple(), f, c);
			return true;
		}
		else return false;
	}
		
	//Crea una nueva célula compleja en el mundo
	public boolean crearCelulaCompleja(int f, int c) {
		if(this.superficie.posValida(f,c) && this.superficie.posLibre(f,c)) {
			this.superficie.insertarCelula(new CelulaCompleja(), f, c);
			return true;
		}
		else return false;
	}
	

	/**
	 * Elimina una celula del mundo (para usar desde fuera)
	 * @param f Fila
	 * @param c Columna
	 * @return Devuelve si se movio la celula
	 */
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
	
	
	/*
	 Recorre la superficie en busca de células.
	 Devuelve un array con las posiciones ocupadas.
	*/
	private ListaCasillas inspecSuperficie() {
		int N = this.superficie.nCelulas();	/*Número total de células en la superficie*/
		ListaCasillas ocupadas = new ListaCasillas(N);
		int n = 0;				/*Contador de número de células*/
		int j;					/*Contador bucle while*/
		int F = this.superficie.getFilas();	/*Número de  filas*/
		int C = this.superficie.getColumnas();	/*Número de columnas*/
		
		for(int i = 0; i<F; i++) {
			j = 0;
			while(j<C && n<N) {
				/*Si encuentra una posición ocupada (una célula)*/
				if(!this.superficie.posLibre(i,j)) {
					ocupadas.add(new Casilla(i,j), n);
					n++;
				}
				j++;
			}
		}
		return ocupadas;
	}
	
	/**
	 * Limpia la superficie del mundo, dejándolo sin células
	 */
	public void vaciar(){
		this.superficie.vaciar();
	}
	
	/**
	 * Da un paso en la evolución del mundo para todas las células.
	 */
	public void evoluciona() {
		/*
		 Foto del número de células en la superficie. Podría ser modificado 
		 durante el bucle dando lugar a errores.
		*/
		ListaCasillas ocupadas;
		int x;
		Casilla nuevaPos;
		
		/*Busca las células que hay en el mundo*/
		ocupadas = inspecSuperficie();
		
		/*Y aplica la lógica para cada una de ellas*/
		for(int i=0; i<ocupadas.len(); i++) {
			nuevaPos = this.superficie.ejecutaMovimiento(ocupadas.get(i));
			
			/*Si se ha movido*/
			if(nuevaPos!=null) {
				/*Asegurándose de que la posición a la que se ha movido
				  no está en la lista para que no se mueva dos veces*/
				if(nuevaPos.greater(ocupadas.get(i))) {
					x = ocupadas.buscar(nuevaPos);
					if(x>=0)
						ocupadas.eliminarCasilla(x);
				}
			}
		}
	}

	
	/**
	 * Representa en un String la superficie.
	 */
	public String toString() {
		return this.superficie.toString();
	}
}

