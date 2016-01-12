package tp.pr1.logica;

import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.IndicesFueraDeRango;

public class MundoComplejo extends Mundo {
	
	private final int N_CELULAS_SIMPLES ;
	private final int N_CELULAS_COMPLEJAS;
	
	public MundoComplejo() {
		super();
		this.N_CELULAS_SIMPLES = 0;
		this.N_CELULAS_COMPLEJAS = 0;
	}
	
	public MundoComplejo(int f, int c) {
		super(f, c);
		this.N_CELULAS_SIMPLES = (f * c) / 5;
		this.N_CELULAS_COMPLEJAS = (f * c) / 5;
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
				if(this.crearCelula(new CelulaSimple(), f,c)) {
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
				if(this.crearCelula(new CelulaSimple(), f,c)) {
					i++;
				}
			} catch (IndicesFueraDeRango e) {}
		}
	}
	
}

