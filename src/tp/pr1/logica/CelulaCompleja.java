package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;

import tp.pr1.excepciones.IndicesFueraDeRango;

public class CelulaCompleja implements Celula {
	
	private final static int MAX_COMER = 3;
	private int celsComidas;
	
	public CelulaCompleja() {
		this.celsComidas = 0;
	}
	
	public boolean esComestible() {
		return false;
	}
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) throws IndicesFueraDeRango{
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

	
	public void save(FileWriter file) throws IOException {
		file.write("compleja " + this.celsComidas);
		file.write(System.getProperty("line.separator"));
	}
}
