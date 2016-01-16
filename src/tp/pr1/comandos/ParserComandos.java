package tp.pr1.comandos;

import tp.pr1.excepciones.FormatoNumericoIncorrecto;

public class ParserComandos {
	private static final Comando[] comandos = {
			new ComandoPaso(),
			new ComandoIniciar(),
			new ComandoVaciar(),
			new ComandoAyuda(),
			new ComandoCrearCelula(),
			new ComandoEliminarCelula(),
			new ComandoSalir()
	};
	
	
	public static String AyudaComandos() {
		StringBuilder ayuda = new StringBuilder();
		for(int i = 0; i<ParserComandos.comandos.length; i++) {
			ayuda.append(ParserComandos.comandos[i].textoAyuda());
			ayuda.append("\n");
		}
		return ayuda.toString();
	}
	
	
	public static Comando parseaComando(String[] cadenas) throws FormatoNumericoIncorrecto {
		int i = 0;
		Comando comando = null;
		
		while(i<ParserComandos.comandos.length && comando == null) {
			comando = ParserComandos.comandos[i].parsea(cadenas);
			i++;
		}
		return comando;
	}
}
