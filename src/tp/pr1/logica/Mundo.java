package tp.pr1.logica;
import tp.pr1.logica.Casilla;

public class Mundo {
	
	private final static int N_FILAS=3;
	private final static int N_COLUMNAS=4;
	
	private final static int DEF_CELULAS=6;
	
	private Superficie superficie = new Superficie(N_FILAS,N_COLUMNAS);
	
	public void evoluciona() {
		//Evolucionar aquí <---
	}
	
	//Crea una nueva célula en el mundo
	public boolean crearCelula(int fila, int columna) {
		return this.superficie.insertarCelula(new Celula(), fila, columna);
	}

	
	//Devuelve un entero aleatorio entre min y max
	private static int numAleatorio(int min, int max) {
		if(min<max)
			return (int)Math.random()*(max-min+1)+min;	
		else
			return 0;
	}
	
	//Crea DEF_CELULAS células en posiciones aleatorias
	public void iniciar() {
		boolean desocupada = true;
		int i = 0;
		int f, c;
		while(i<Mundo.DEF_CELULAS && desocupada) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			desocupada = crearCelula(f,c);
			if(desocupada)
				i++;
		}
	}
	
	/*Revisa los alrededores de una célula en busca de
	una posición libre aleatoria*/
	public boolean inspeccionarAlrededores(Casilla pos) {
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
			for(int i = topF; i<bottomF; i++) {
				for(int j = leftC; j<rightC; j++) {
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
	
	public void moverCelula(int f, int c) {
		
	}
}
