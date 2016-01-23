/*
    celulas-tp - A simple console Game of Life.
    Copyright (C) 2015-2016  Guillermo Alonso and Germán Franco

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.IndicesFueraDeRango;

public class CelulaSimple implements Celula {
	private static final short MAX_PASOS_SIN_MOVER = 2;
	private static final short PASOS_REPRODUCCION = 4;

	private short pasosReprod;	//Pasos que le quedan para dividirse (pasos movidos)
	private short pasosMuerte;	//Pasos que le quedan para morir (pasos sin moverse)
	
	/**
	 * Construye una nueva célula simple.
	 */
	public CelulaSimple() {
		this.pasosReprod = CelulaSimple.PASOS_REPRODUCCION;
		this.pasosMuerte = CelulaSimple.MAX_PASOS_SIN_MOVER;
	}
	
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) throws IndicesFueraDeRango {
		Casilla pos = null;
		/*Si le quedan movimientos */
		if(this.pasosMuerte > 0) {
			pos = new Casilla(f,c);
			
			/*Si le toca reproducirse */
			if(this.pasosReprod <= 0) {
				/*Se mueve la madre y nace la hija*/
				if(CelulaSimple.moverCelula(pos,superficie) != null) {
					superficie.insertar(new CelulaSimple(),f,c);
					this.pasosReprod = CelulaSimple.PASOS_REPRODUCCION;
					System.out.println("Nace nueva célula en (" + f + "," + c + ") " + 
							"cuyo padre ha sido " + pos);
				}
				/*Si no se puede dividir, muere*/
				else {
					superficie.eliminar(f,c);
					System.out.println("Muere la célula de la casilla (" + f + "," + c + ") " + 
							"por no poder reproducirse");
				}
				
			}
			/*Si no le tocaba reproducirse, intenta moverse */
			else if(CelulaSimple.moverCelula(pos, superficie) != null) {
				this.pasosReprod--;
				System.out.println("Movimiento de (" + f + "," + c + ") a " + pos);
			}
			/*Si no se reproduce ni se mueve*/
			else {
				this.pasosMuerte--;
			}
		}
		/*Si no le quedan pasos se muere*/
		else {
			superficie.eliminar(f,c);
			System.out.println("Muere la célula de la casilla (" + f + "," + c + ") " + 
					"por inactividad");
		}
		return pos;
	}
	
	
	/**
	 * INTENTA mover una célula
	 * @param pos Posición de entrada y salida, null si no se ha movido
	 * @return Devuelve el parámetro pos también.
	 * @throws IndicesFueraDeRango 
	 */
	private static Casilla moverCelula(Casilla pos, Superficie superficie) throws IndicesFueraDeRango {
		/*Copiamos la posicion original*/
		int f = pos.getFila();
		int c = pos.getColumna();

		/*Busca una casilla a la que moverse*/
		Casilla nuevaPos = CelulaSimple.inspecAlrededores(f,c,superficie);
		/*Si se puede mover porque hay un hueco*/
		if(nuevaPos!=null) {
			pos.set(nuevaPos); /*Para devolverlo por el parámetro*/
			superficie.mover(f, c, pos.getFila(), pos.getColumna());
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
	 * @throws IndicesFueraDeRango Si la posición está fuera de la superficie
	 */
	private static Casilla inspecAlrededores(int f, int c, Superficie superficie) throws IndicesFueraDeRango {
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
		return true;
	}
	
	public String toString() {
		return "[" + this.pasosMuerte + "-" + this.pasosReprod + "]";
	}
	

	public void save(FileWriter file) throws IOException {
			file.write("simple " + this.pasosMuerte + " " + this.pasosReprod);
	}
	
	public void cargar(Scanner archivo) throws ErrorDeCarga {
		try {
			this.pasosMuerte = archivo.nextShort();
			this.pasosReprod = archivo.nextShort();
		} catch (NoSuchElementException e) {
			throw new ErrorDeCarga();
		}
		
		if(this.pasosMuerte < 0 || this.pasosMuerte > MAX_PASOS_SIN_MOVER ||
		this.pasosReprod < 0 || this.pasosReprod > PASOS_REPRODUCCION) {
			throw new ErrorDeCarga();
		}
	}
}
