package tp.pr1.comandos;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.ErrorDeCarga;
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.IndicesFueraDeRango;
import tp.pr1.excepciones.PalabraIncorrecta;

public class ComandoJugar extends Comando {

	@Override
	public void ejecuta(Controlador cntrl) throws IndicesFueraDeRango, ErrorDeInicializacion, PalabraIncorrecta,
			FileNotFoundException, IOException, ErrorDeCarga {
		
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto {
		
		return null;
	}

	@Override
	public String textoAyuda() {
		return "Comando Jugar: introduce el tipo de mundo, la fila y columnas y las celulas, ej: jugar simple 2 2 2";
	}
	
}
