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
import tp.pr1.excepciones.PalabraIncorrecta;

public class MundoComplejo extends Mundo {
	
	private final int N_CELULAS_SIMPLES ;
	private final int N_CELULAS_COMPLEJAS;
	
	protected Scanner in = new Scanner (System.in);
	
	/**
	 * Construye un mundo complejo con una superficie.
	 */
	public MundoComplejo() {
		super();
		this.N_CELULAS_SIMPLES = 0;
		this.N_CELULAS_COMPLEJAS = 0;
	}
	
	/**
	 * Construye un mundo complejo con una superficie.
	 * @param f Número de filas.
	 * @param c Número de columnas.
	 */
	public MundoComplejo(int f, int c) {
		super(f, c);
		this.N_CELULAS_SIMPLES = (f * c) / 5;
		this.N_CELULAS_COMPLEJAS = (f * c) / 5;
	}
	
	public MundoComplejo(int f, int c, int nCelulasSimples, int nCelulasComplejas) {
		super(f, c);
		this.N_CELULAS_SIMPLES = nCelulasSimples;
		this.N_CELULAS_COMPLEJAS = nCelulasComplejas;
	}
	

	public void iniciar() throws ErrorDeInicializacion {
		int i = 0;
		int f, c;
		
		this.superficie.vaciar();
		
		if(!this.superficie.hayCapacidad(this.N_CELULAS_SIMPLES+this.N_CELULAS_COMPLEJAS)) {
			throw new ErrorDeInicializacion();
		}
		
		/*Crea las células complejas*/
		while(i<this.N_CELULAS_COMPLEJAS) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			try {
				if(this.superficie.posLibre(f, c)) {
					this.superficie.insertar(new CelulaCompleja(), f,c);
					i++;
				}
			} catch (IndicesFueraDeRango e) {}
		}
		/*Crea las células simples*/
		i = 0;
		while(i<(this.N_CELULAS_SIMPLES)) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			try {
				if(this.superficie.posLibre(f, c)) {
					this.superficie.insertar(new CelulaSimple(), f,c);
					i++;
				}
			} catch (IndicesFueraDeRango e) {}
		}
	}
	
	public void cargar(Scanner archivo) throws ErrorDeCarga{
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
 			else if(tCelula.equals("compleja")) {
 				celula = new CelulaCompleja();
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
	
	public void guardar(FileWriter file) throws IOException{
		file.write("complejo");
		file.write(System.getProperty("line.separator"));
		file.write(String.valueOf(this.N_FILAS));
		file.write(System.getProperty("line.separator"));
		file.write(String.valueOf(this.N_COLUMNAS));
		file.write(System.getProperty("line.separator"));
		this.superficie.save(file);
	}
	
	
	public boolean crearCelula(int f, int c) throws IndicesFueraDeRango, PalabraIncorrecta {
		String tipoCelula;
		if(this.superficie.posLibre(f,c)) {
			System.out.print("¿Qué tipo de célula? > ");
			tipoCelula = in.nextLine();
			if (tipoCelula.equals("simple")) {
				this.superficie.insertar(new CelulaSimple(), f, c);
			}
			else if (tipoCelula.equals("compleja")) {
				this.superficie.insertar(new CelulaCompleja(), f, c);
			}
			else throw new PalabraIncorrecta(tipoCelula);
			return true;
		}
		else return false;
	}
}

