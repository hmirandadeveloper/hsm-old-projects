package negocio.exception;

public class UsuarioSemPremissaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioSemPremissaoException()
	{
		super("Usuário sem permissão para essa operação!");
	}

}
