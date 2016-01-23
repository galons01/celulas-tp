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

import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.IndicesFueraDeRango;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;
import tp.pr1.excepciones.PalabraIncorrecta;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr1.control.Controlador;

public abstract class Comando {
	
	/**
	 * Ejecuta las acciones del comando.
	 * @param cntrl Controlador sobre el que se ejecuta la acción.
	 * @throws IndicesFueraDeRango Si los parámetros están fuera del mundo.
	 * @throws ErrorDeInicializacion Si al ejecutar el comando el mundo es erróneo.
	 * @throws PalabraIncorrecta Si la palabra no es correcta
	 * @throws IOException Si no se pudo guardar el archivo con la partida.
	 * @throws ErrorDeCarga Si no se pudo cargar el archivo con la partida.
	 * @throws FileNotFoundException Si no se encontró el archivo especificado. 
	 */
	public abstract void ejecuta(Controlador cntrl) throws IndicesFueraDeRango, ErrorDeInicializacion, PalabraIncorrecta, FileNotFoundException, IOException, ErrorDeCarga, NumeroParametrosIncorrecto;
	
	
	/**
	 * Parsea el comando, de forma que si los parámetros 
	 * coinciden, se devuelve el comando.
	 * @param cadenaComando Cadena con los parámetros del comando.
	 * @return Comando de la subclase que coincida.
	 * @throws FormatoNumericoIncorrecto Si se ha introducido algo que no es 
	 * un número entero.
	 * @throws NumeroParametrosIncorrecto Si el número de parámetros introducido no es correcto.
	 */
	public abstract Comando parsea(String[ ] cadenaComando) throws FormatoNumericoIncorrecto, NumeroParametrosIncorrecto;
	
	
	/**
	 * Genera la ayuda del comando.
	 * @return String con el texto de ayuda.
	 */
	public abstract String textoAyuda();
	
	
	/**
	 * Comprueba si dos String son iguales (case insensitive)
	 * @param a String 1
	 * @param b String 2
	 * @return true si son iguales, false si no lo son
	 */
	public static boolean igualesIns(String a, String b) {
		return a.toLowerCase().equals(b.toLowerCase());
	}
}
