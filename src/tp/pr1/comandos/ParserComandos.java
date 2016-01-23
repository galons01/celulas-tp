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

import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;

public class ParserComandos {
	private static final Comando[] comandos = {
			new ComandoPaso(),
			new ComandoJugar(),
			new ComandoIniciar(),
			new ComandoVaciar(),
			new ComandoCargar(),
			new ComandoGuardar(),
			new ComandoAyuda(),
			new ComandoCrearCelula(),
			new ComandoEliminarCelula(),
			new ComandoSalir()
	};
	
	
	public static String AyudaComandos() {
		StringBuilder ayuda = new StringBuilder();
		for(int i = 0; i<ParserComandos.comandos.length; i++) {
			ayuda.append(ParserComandos.comandos[i].textoAyuda());
			ayuda.append("\n");
		}
		return ayuda.toString();
	}
	
	
	public static Comando parseaComando(String[] cadenas) throws FormatoNumericoIncorrecto, NumeroParametrosIncorrecto {
		int i = 0;
		Comando comando = null;
		
		while(i<ParserComandos.comandos.length && comando == null) {
			comando = ParserComandos.comandos[i].parsea(cadenas);
			i++;
		}
		return comando;
	}
}
