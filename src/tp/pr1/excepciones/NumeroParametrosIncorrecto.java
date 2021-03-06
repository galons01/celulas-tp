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

package tp.pr1.excepciones;

public class NumeroParametrosIncorrecto extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3277414083560276504L;

	@Override
	public String getMessage() {
		return "EXCEPCIÓN: Número de parámetros incorrecto. ";
		
	}
	
}
