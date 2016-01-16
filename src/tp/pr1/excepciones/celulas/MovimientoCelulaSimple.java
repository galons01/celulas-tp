package tp.pr1.excepciones.celulas;
import tp.pr1.logica.Casilla;;

public final class MovimientoCelulaSimple extends StepLogException {
	
	private Casilla posOrig, posDest;
	
	public MovimientoCelulaSimple(Casilla origen, Casilla destino) {
		this.posOrig = origen;
		this.posDest = destino;
	}
	public String getMessage() {
		return "Celula simple en " + this.posOrig + " se mueve a " + this.posDest;
	}

	public Casilla getDestino() {
		return this.posDest;
	}
}