package tp.pr1.excepciones;

public class IndicesFueraDeRango extends Exception {
	
	@Override
	public String getMessage() {
		return "EXCEPCIÓN: Índices fuera de rango. ";
		
	}
	
}
