package tp.pr1.excepciones;

public class ErrorDeInicializacion extends Exception {
	
	@Override
	public String getMessage() {
		return "Capacidad máxima alcanzada. Error de inicialización. ";
		
	}
	
}
