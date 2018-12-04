package negocio.exception;

public class EntidadeAtributoUnicoDuplicadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntidadeAtributoUnicoDuplicadoException(String atributo)
	{
		super("Cadastro não pôde ser realizado, pois o atributo " +
				atributo.toUpperCase() + ", que deve ser único, está duplicado! ");
	}

}
