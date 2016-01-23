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

public class Casilla {
	private int fila = 0;
	private int col = 0;
	
	/**
	 * Constructor de casilla. Crea una casilla que contiene una posición en dos dimensiones.
	 * @param f Fila.
	 * @param c Columna.
	 */
	public Casilla (int f, int c) {
		this.fila = f;
		this.col = c;
	}
	
	/**
	 * Copia una casilla en la casilla actual
	 * @param c Casilla a copiar
	 */
	public void set(Casilla c) {
		this.fila = c.fila;
		this.col = c.col;
	}
	
	/**
	 * Getter de la columna
	 * @return Un entero representando una columna
	 */
	public int getColumna() {
		return this.col;
	}
	
	/**
	 * Getter de la fila
	 * @return Un entero representando una fila
	 */
	public int getFila() {
		return this.fila;
	}
	
	
	/**
	 * Devuelve si es mayor que la casilla del parámetro.
	 * La fila tiene prioridad sobre la columna, por lo que para ser mayor
	 * deberá estar por debajo o en la misma fila pero mayor columna.
	 * @param c Casilla con la que compara
	 * @return true si es mayor, false en caso cotrario
	 */
	public boolean greater(Casilla c) {
		return this.fila > c.fila || this.fila == c.fila && this.col > c.col; 
	}
	
	
	/**
	 * Comprueba si las casillas son iguales.
	 * @param c Casilla con la que compara
	 * @return true si son iguales, false si son distintas
	 */
	public boolean equals(Casilla c) {
		return this.fila == c.fila && this.col == c.col;
	}
	
	public String toString() {
		return "(" + this.fila + "," + this.col + ")";
	}
	
}
