package tp.pr1.excepciones;

public class ErrorDeInicializacion extends Exception {
	
	@Override
	public String getMessage() {
		return "EXCEPCIÓN: Error de inicialización. Capacidad máxima alcanzada. ";
		
	}
	
}
