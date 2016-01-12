package tp.pr1.excepciones.celulas;

import tp.pr1.logica.Casilla;

public abstract class StepLogException extends Exception{
	
	public abstract String getMessage();
	
	/**
	 * Devuelve la posición de destino del movimiento realizado.
	 * @return Posición o null si no ha habido movimiento.
	 */
	public abstract Casilla getDestino();
}
