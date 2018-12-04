package negocio.util.validador.entidade;

import java.util.List;

import dto.TipoPagamentoDTO;

public abstract class TipoPagamentoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(TipoPagamentoDTO tipoPagamentoDTO)
	{
		boolean atributosCorretos = true;
		
		if(tipoPagamentoDTO == null)
		{
			return false;
		}
		
		if(tipoPagamentoDTO.getDescricao() == null || 
				tipoPagamentoDTO.getDescricao().equals(""))
		{
			atributosCorretos = false;
		}
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(tipoPagamentoDTO.getEstabelecimentoDTO()))
		{
			atributosCorretos = false;
		}
		
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<TipoPagamentoDTO> tipoPagamentoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(TipoPagamentoDTO tipoPagamentoDTO : tipoPagamentoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(tipoPagamentoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
