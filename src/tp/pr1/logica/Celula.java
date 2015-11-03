package tp.pr1.logica;

public class Celula {
	private static final short MAX_PASOS_SIN_MOVER = 3;
	private static final short PASOS_REPRODUCCION = 4;

	private short pasosReprod;	//Pasos que le quedan para dividirse (pasos movidos)
	private short pasosMuerte;	//Pasos que le quedan para morir (sin moverse)
	
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

	public boolean darPaso() {
		if(this.pasosReprod <= 0)
			return false;
		else {
			this.pasosReprod--;
			return true;
		}
	}
	
	public boolean estarQuieta() {
		if(this.pasosMuerte <= 0)
			return false;
		else {
			this.pasosMuerte--;
			return true;
		}
	}
	
}
