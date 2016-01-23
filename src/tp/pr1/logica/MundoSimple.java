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
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.IndicesFueraDeRango;

public class MundoSimple extends Mundo {
	
	private final int N_CELULAS;
	
	/**
	 * Construye un mundo simple con una superficie.
	 */
	public MundoSimple() {
		super();
		this.N_CELULAS = 0;
	}
	
	/**
	 * Construye un mundo simple con una superficie.
	 * @param f Número de filas.
	 * @param c Número de columnas.
	 */
	public MundoSimple(int f, int c) {
		super(f, c);
		this.N_CELULAS = (f * c) / 3;
	}
	
	public MundoSimple(int f, int c, int nCelulas) {
		super(f, c);
		this.N_CELULAS = nCelulas;
	}
	

	public void iniciar() throws ErrorDeInicializacion {
		int i;
		int f, c;
		
		this.superficie.vaciar();
		
		if(!this.superficie.hayCapacidad(this.N_CELULAS)) {
			throw new ErrorDeInicializacion();
		}

		i = 0;
		while(i<this.N_CELULAS) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			try {
				if(this.crearCelula(f,c)) {
					i++;
				}
			} catch (IndicesFueraDeRango e) {}
		}
	}


	public void cargar(Scanner archivo) throws ErrorDeCarga {
		int i,j;
		String tCelula;
		Celula celula;
		
 		while(archivo.hasNext()){
 			try {
 				i = archivo.nextInt();
 				j = archivo.nextInt();
 				tCelula = archivo.next();
	 		} catch (NoSuchElementException e) {
				throw new ErrorDeCarga();
			}
 			
 			if(tCelula.equals("simple")) {
 				celula = new CelulaSimple();
			}
 			else throw new ErrorDeCarga();
 			
 			celula.cargar(archivo);
	 		try {
	 			this.superficie.insertar(celula, i, j);
 			} catch (IndicesFueraDeRango e) {
 				throw new ErrorDeCarga();
 			}
 		}
 	}
	
	@Override
	public boolean crearCelula( int f, int c) throws IndicesFueraDeRango {
		if(this.superficie.posLibre(f,c)) {
			this.superficie.insertar(new CelulaSimple(), f, c);
			return true;
		}
		else return false;
	}
	
	public void guardar(FileWriter file) throws IOException{
		file.write("simple");
		file.write(System.getProperty("line.separator"));
		file.write(String.valueOf(this.N_FILAS));
		file.write(System.getProperty("line.separator"));
		file.write(String.valueOf(this.N_COLUMNAS));
		file.write(System.getProperty("line.separator"));
		this.superficie.save(file);
	}
}

