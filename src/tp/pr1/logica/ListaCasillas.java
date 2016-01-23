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


public class ListaCasillas {
	Casilla[] lista;
	int longitud;
	
	/**
	 * Constructor de una lista de casillas.
	 * @param n Longitud de la lista.
	 */
	public ListaCasillas(int n) {
		this.lista = new Casilla[n];
		this.longitud = 0;
	}
	
	
	/**
	 * Inserta una casilla en la posicion indicada de la lista
	 * @param c Casilla que se va añadir
	 * @param p Posición en la que se inserta 
	 * @return true si cabe, false en caso contrario
	 */
	public boolean add(Casilla c, int p) {
		if(this.longitud<this.lista.length && p < this.lista.length) {
			for(int i=this.longitud-2; i>=p; i--) {
				this.lista[i+1] = this.lista[i];
			}
			this.lista[p] = c;
			this.longitud++;
			return true;
		}
		else return false;
	}
	
	
	/**
	 * Elimina una casilla de la lista
	 * @param pos Posición de la casilla que se quiere eliminar.
	 */
	public void eliminarCasilla(int pos) {
		if(this.longitud > 0) {
			for(int i = pos; i<this.longitud-1; i++) {
				this.lista[i] = this.lista[i+1];
			}
			this.longitud--;
		}
	}
	
	
	/**
	 * Busca una casilla en la lista
	 * @param buscada Casilla a buscar
	 * @return posición en la que se encuentra. Si no está devuelve -1
	 */
	public int buscar(Casilla buscada){
		int i = 0;
		boolean encontrada = false;
		while(i<this.longitud && !encontrada) {
			encontrada = buscada.equals(this.lista[i]);
			i++;
		}
		if(encontrada)
			return i-1;
		else return -1;
	}
	
	
	/**
	 * Devuelve la casilla en la posición de la lista
	 * @param pos Posición a la que se quiere acceder
	 * @return Casilla en la posición.
	 */
	public Casilla get(int pos) {
		if(pos < this.longitud)
			return this.lista[pos];
		else return null;
	}
	
	
	/**
	 * Devuelve la longitud ocupada de la lista.
	 * @return Entero que representa la longitud.
	 */
	public int len() {
		return this.longitud;
	}
	
	
}

