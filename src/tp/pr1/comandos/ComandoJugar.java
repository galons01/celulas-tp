package tp.pr1.comandos;

import tp.pr1.control.Controlador;
import tp.pr1.excepciones.ErrorDeInicializacion;
import tp.pr1.excepciones.FormatoNumericoIncorrecto;
import tp.pr1.excepciones.NumeroParametrosIncorrecto;
import tp.pr1.excepciones.PalabraIncorrecta;

public class ComandoJugar extends Comando {
	
	private int filas, columnas;
	private int [] nCelulas;
	private String tMundo;
	

	public ComandoJugar() {
		this.filas = 0;
		this.columnas = 0;
		this.nCelulas = null;
		this.tMundo = "";
	}
	/**
	 * @param tMundo String con el nombre del tipo de mundo.
	 * @param f Número de filas.
	 * @param c Número de columnas.
	 * @param nCelulas Número de células en el mundo. Cada posición de larray representa un tipo de célula.
	 */
	public ComandoJugar(String tMundo, int f, int c, int[] nCelulas) {
		this.filas = f;
		this.columnas = c;
		this.nCelulas = new int[nCelulas.length];
		for(int i = 0; i<nCelulas.length; i++) {
			this.nCelulas[i] = nCelulas[i];
		}
		this.tMundo = tMundo;
	}
	
	
	public void ejecuta(Controlador cntrl) throws PalabraIncorrecta, ErrorDeInicializacion, NumeroParametrosIncorrecto  {
		cntrl.nuevoMundo(this.tMundo, this.filas, this.columnas, this.nCelulas);
		cntrl.iniciar();
	}

	/**
	 * Parsea el comando.
	 * @param cadenaComando Array de strings con el comando y los parámetros.
	 * @return Objeto ComandoIniciar si procede o null.
	 * @throws FormatoNumericoIncorrecto  
	 */
	public Comando parsea(String[] cadenaComando) throws FormatoNumericoIncorrecto, NumeroParametrosIncorrecto {
		int f,c;
		int [] nCelulas;
		if(igualesIns(cadenaComando[0],"JUGAR")) {
			if(cadenaComando.length > 4) {
				try {
					f = Integer.parseInt(cadenaComando[2]);
					c = Integer.parseInt(cadenaComando[3]);
					nCelulas = new int[cadenaComando.length-4];
					for(int i = 4; i<cadenaComando.length; i++) {
						nCelulas[i-4] = Integer.parseInt(cadenaComando[i]);
					}
				} catch ( NumberFormatException e ) {
					throw new  FormatoNumericoIncorrecto();
				}
				return new ComandoJugar(cadenaComando[1],f,c, nCelulas);
			}
			else throw new NumeroParametrosIncorrecto();
		}
		else return null;
	}

	/**
	 * Texto de ayuda del comando.
	 * @return String con la descripción.
	 */
	public String textoAyuda() {
		return "JUGAR: Crea un mundo nuevo. ";
	}
}