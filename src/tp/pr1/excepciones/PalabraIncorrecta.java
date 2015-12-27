package tp.pr1.excepciones;

public class PalabraIncorrecta extends Exception {
	
	@Override
	public String getMessage() {
		return "Palabra incorrecta. ";
		
	}
	
}
