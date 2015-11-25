package tp.pr1.logica;

public class Celula {
	private static final short MAX_PASOS_SIN_MOVER = 2;
	private static final short PASOS_REPRODUCCION = 4;

	private short pasosReprod;	//Pasos que le quedan para dividirse (pasos movidos)
	private short pasosMuerte;	//Pasos que le quedan para morir (pasos sin moverse)
	
	public Celula() {
		this.pasosReprod = Celula.PASOS_REPRODUCCION;
		this.pasosMuerte = Celula.MAX_PASOS_SIN_MOVER;
	}
	
	public short getPasosReprod() { 
		return this.pasosReprod;
	}

	public short getPasosMuerte() {
		return this.pasosMuerte;
	}
	
	public boolean puedeReprod() { 
		return this.pasosReprod == 0;
	}

	public boolean puedeMoverse() {
		return this.pasosMuerte > 0;
	}
	
	/*Resta un paso para la reproduccion de la célula */
	public void darPaso() {
		if(this.pasosReprod > 0)
			this.pasosReprod--;
	}
	
	/*Resta un paso para la muerte de la célula */
	public void estarQuieta() {
		if(this.pasosMuerte > 0)
			this.pasosMuerte--;
	}
	
	/*Reproduce una célula si puede*/
	public boolean reproducir() {
		if(this.pasosReprod == 0) {
			this.pasosReprod = Celula.PASOS_REPRODUCCION;
			return true;
		}
		return false;
	}
	
}
