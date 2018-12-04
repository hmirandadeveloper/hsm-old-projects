package negocio.exception;

public class CnpjInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CnpjInvalidoException()
	{
		super("CNPJ inválido!");
	}

}
