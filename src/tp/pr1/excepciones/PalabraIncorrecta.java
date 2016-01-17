package tp.pr1.excepciones;

public class PalabraIncorrecta extends Exception {
	
	private String palabra;
	
	public PalabraIncorrecta(String palabra) {
		this.palabra = " \"" + palabra + "\" ";
	}
	
	public PalabraIncorrecta() {
		this.palabra = " ";
	}
	
	@Override
	public String getMessage() {
		return "EXCEPCIÓN: Palabra" + this.palabra + "no reconocida. ";
		
	}
	
}
