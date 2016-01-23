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

import java.io.IOException;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;;

public class ComandoGuardar extends Comando {
	
	private String nombreArchivo;
	
	public ComandoGuardar(String nombre) {
		this.nombreArchivo = nombre;
	}
	
	public ComandoGuardar() {
		this.nombreArchivo = "unnamed.txt";
	}
	
	public void ejecuta(Controlador cntrl) throws IOException {
		cntrl.guardarMundo(this.nombreArchivo);
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null. 
	 */
	public Comando parsea(String[] cadenaComando) throws NumeroParametrosIncorrecto {
		if(igualesIns(cadenaComando[0],"GUARDAR")) {
			if(cadenaComando.length == 2) {
				return new ComandoGuardar(cadenaComando[1]);
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
		return "GUARDAR: Guardar el mundo en un arhivo. ";
	}

}
