package negocio.exception;

public class EntidadeAtributoIncompletoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	public EntidadeAtributoIncompletoException(Class entidade)
	{
		super(entidade.getCanonicalName() + " com atributo(s) obrigatorio(s) " +
				"incompleto(s)!");
	}

}
