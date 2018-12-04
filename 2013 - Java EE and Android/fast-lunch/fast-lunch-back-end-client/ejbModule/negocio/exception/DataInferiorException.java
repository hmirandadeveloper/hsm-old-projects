package negocio.exception;

public class DataInferiorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	public DataInferiorException(Class entidade)
	{
		super(entidade.getCanonicalName() + " com data inferior!");
	}

}
