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

public class CelulaCompleja implements Celula {
	
	private final static int MAX_COMER = 3;
	private int celsComidas;
	
	/**
	 * Construye una nueva célula compleja.
	 */
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
	}
	
	public void cargar(Scanner archivo) throws ErrorDeCarga {
		try {
			this.celsComidas =  archivo.nextShort();
		} catch (NoSuchElementException e) {
			throw new ErrorDeCarga();
		}
		if(this.celsComidas < 0 || this.celsComidas > MAX_COMER) {
			throw new ErrorDeCarga();
		}
	}
}
