package tp.pr1.logica;

import tp.pr1.excepciones.ErrorDeInicializacion;

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
			if(this.crearCelula(new CelulaSimple(), f,c))
				i++;
		}
	}
}

