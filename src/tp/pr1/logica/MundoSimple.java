package tp.pr1.logica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr1.excepciones.ErrorCargar;
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.IndicesFueraDeRango;

public class MundoSimple extends Mundo {
	
	private final int DEF_CELULAS;
	
	
	public MundoSimple() {
		super();
		this.DEF_CELULAS = 0;
	}
	
	public MundoSimple(int f, int c) {
		super(f, c);
		this.DEF_CELULAS = (f * c) / 3;
	}
	

	public void iniciar() throws ErrorDeInicializacion {
		int i;
		int f, c;
		
		this.superficie.vaciar();
		
		if(!this.superficie.hayCapacidad(this.DEF_CELULAS)) {
			throw new ErrorDeInicializacion();
		}

		i = 0;
		while(i<this.DEF_CELULAS) {
			f = numAleatorio(0,this.superficie.getFilas()-1);
			c = numAleatorio(0,this.superficie.getColumnas()-1);
			try {
				if(this.crearCelula(new CelulaSimple(), f,c)) {
					i++;
				}
			} catch (IndicesFueraDeRango e) {}
		}
	}

public void cargar(Scanner archivo) throws ErrorCargar{
	int x,y, pasos, pasosSinMoverse = 0;
	String tipoCelula = null;
	
		while(archivo.hasNext()){
			x = archivo.nextInt();
			y = archivo.nextInt();
			tipoCelula = archivo.next();
			try {
 			if(tipoCelula=="simple"){
 				crearCelula(new CelulaSimple(), x, y);
			}
 
 			else{
 				throw new ErrorCargar();
 			}
 			
			} catch (IndicesFueraDeRango e) {
				throw new ErrorCargar();
			}
		}
	}
public void guardar(FileWriter file) throws IOException{
		file.write("simple");
		file.write(System.getProperty("line.separator"));
		file.write(N_FILAS);
		file.write(System.getProperty("line.separator"));
		file.write(N_COLUMNAS);
		file.write(System.getProperty("line.separator"));
		this.superficie.save(file);
	}
}

