package tp.pr1.excepciones;

public class ErrorCargar extends Exception {
	
	@Override
	public String getMessage() {
		return "Error en la carga del mundo";
		
	}
	
}
