package negocio.util.validador.data;

import java.util.Date;


public abstract class OperacoesData {
	public static final String PADRAO_DATA_DB = "AAAA-MM-DD";
	public static final String PADRAO_DATA_EXIBICAO = "DD/MM/AAAA";
	
	public static boolean validacaoComDataAtual(Date data)
	{
		if(data.getTime()  >= (new Date().getTime() - 1000000))
		{
			return true;
		}
		
		return false;
	}
}
