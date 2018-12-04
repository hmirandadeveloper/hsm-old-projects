package negocio.exception;

public class CpfInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CpfInvalidoException()
	{
		super("CPF inválido!");
	}

}
