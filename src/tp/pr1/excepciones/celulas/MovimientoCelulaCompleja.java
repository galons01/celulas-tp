package tp.pr1.excepciones.celulas;
import tp.pr1.logica.Casilla;;

public final class MovimientoCelulaCompleja extends StepLogException {
	
	private Casilla posOrig, posDest;
	private boolean come;
	
	public MovimientoCelulaCompleja(Casilla origen, Casilla destino, boolean come) {
		this.posOrig = origen;
		this.posDest = destino;
		this.come = come;
	}
	public String getMessage() {
		String m = "Celula compleja en " + this.posOrig + " se mueve a " + this.posDest; 
		return m + " -- " + (come ? "COME" : "NO COME") + " -- "; 
	}

	
	public Casilla getDestino() {
		return this.posDest;
	}
}