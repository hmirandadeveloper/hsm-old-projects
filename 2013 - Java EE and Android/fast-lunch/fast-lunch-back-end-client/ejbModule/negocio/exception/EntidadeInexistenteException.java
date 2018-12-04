package negocio.exception;

public class EntidadeInexistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	public EntidadeInexistenteException(Class entidade)
	{
		super(entidade.getCanonicalName() + " inexistente no Sistema!");
	}

}
