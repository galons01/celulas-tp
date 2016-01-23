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

import java.io.FileNotFoundException;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;;

public class ComandoCargar extends Comando {
	
	private String nombreArchivo;
	
	public ComandoCargar(String nombre) {
		this.nombreArchivo = nombre;
	}
	
	public ComandoCargar() {
		this.nombreArchivo = "unnamed.txt";
	}
	
	public void ejecuta(Controlador cntrl) throws ErrorDeCarga, FileNotFoundException {
		cntrl.cargarMundo(this.nombreArchivo);
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null.
	 */
	public Comando parsea(String[] cadenaComando) throws NumeroParametrosIncorrecto {
		if(igualesIns(cadenaComando[0],"CARGAR")){
			if(cadenaComando.length == 2) {
				return new ComandoCargar(cadenaComando[1]);
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
		return "CARGAR: Cargar un mundo de un archivo.";
	}

}
