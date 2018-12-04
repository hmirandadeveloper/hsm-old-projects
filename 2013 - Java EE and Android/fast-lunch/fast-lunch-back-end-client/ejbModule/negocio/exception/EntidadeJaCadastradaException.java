package negocio.exception;

public class EntidadeJaCadastradaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	public EntidadeJaCadastradaException(Class entidade)
	{
		super(entidade.getName() + " já cadastrada!");
	}

}
