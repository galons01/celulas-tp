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

package tp.pr1.comandos;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;
import tp.pr1.excepciones.PalabraIncorrecta;

public class ComandoJugar extends Comando {
	
	private int filas, columnas;
	private int [] nCelulas;
	private String tMundo;
	

	public ComandoJugar() {
		this.filas = 0;
		this.columnas = 0;
		this.nCelulas = null;
		this.tMundo = "";
	}
	/**
	 * @param tMundo String con el nombre del tipo de mundo.
	 * @param f Número de filas.
	 * @param c Número de columnas.
	 * @param nCelulas Número de células en el mundo. Cada posición de larray representa un tipo de célula.
	 */
	public ComandoJugar(String tMundo, int f, int c, int[] nCelulas) {
		this.filas = f;
		this.columnas = c;
		this.nCelulas = new int[nCelulas.length];
		for(int i = 0; i<nCelulas.length; i++) {
			this.nCelulas[i] = nCelulas[i];
		}
		this.tMundo = tMundo;
	}
	
	
	public void ejecuta(Controlador cntrl) throws PalabraIncorrecta, ErrorDeInicializacion, NumeroParametrosIncorrecto  {
		cntrl.nuevoMundo(this.tMundo, this.filas, this.columnas, this.nCelulas);
		cntrl.iniciar();
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null.
	 * @throws FormatoNumericoIncorrecto  
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto, NumeroParametrosIncorrecto {
		int f,c;
		int [] nCelulas;
		if(igualesIns(cadenaComando[0],"JUGAR")) {
			if(cadenaComando.length > 4) {
				try {
					f = Integer.parseInt(cadenaComando[2]);
					c = Integer.parseInt(cadenaComando[3]);
					nCelulas = new int[cadenaComando.length-4];
					for(int i = 4; i<cadenaComando.length; i++) {
						nCelulas[i-4] = Integer.parseInt(cadenaComando[i]);
					}
				} catch ( NumberFormatException e ) {
					throw new  FormatoNumericoIncorrecto();
				}
				return new ComandoJugar(cadenaComando[1],f,c, nCelulas);
			}
			else throw new NumeroParametrosIncorrecto();
		}
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "JUGAR: Crea un mundo nuevo. ";
	}
}
