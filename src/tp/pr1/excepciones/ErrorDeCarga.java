package tp.pr1.excepciones;

public class ErrorDeCarga extends Exception {
	
	@Override
	public String getMessage() {
		return "EXCEPCIÓN: Error en la carga de archivos. Formato incorrecto. ";
		
	}
	
}
