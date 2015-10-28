package tp.pr1.logica;

public class Celula {
	private int turnosSinMover = 0;
	private int pasos = 0;
	
	static private final short MAX_PASOS_SIN_MOVER = 3;
	static private final short PASOS_REPRODUCCION = 6;
	
	
	
	
	public int pasosDados() { 
		return this.pasos;
	}
	
}