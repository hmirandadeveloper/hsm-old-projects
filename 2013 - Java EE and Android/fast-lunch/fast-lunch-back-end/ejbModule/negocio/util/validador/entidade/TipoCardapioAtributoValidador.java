package negocio.util.validador.entidade;

import java.util.List;

import dto.TipoCardapioDTO;

public abstract class TipoCardapioAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(TipoCardapioDTO tipoCardapioDTO)
	{
		boolean atributosCorretos = true;
		
		if(tipoCardapioDTO == null)
		{
			return false;
		}
		
		if(tipoCardapioDTO.getDescricao() == null || 
				tipoCardapioDTO.getDescricao().equals(""))
		{
			atributosCorretos = false;
		}
		if(tipoCardapioDTO.getNome() == null || 
				tipoCardapioDTO.getNome().equals(""))
		{
			atributosCorretos = false;
		}
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(tipoCardapioDTO.getEstabelecimentoDTO()))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<TipoCardapioDTO> tipoCardapioDTOs)
	{
		boolean atributosCorretos = true;
		
		for(TipoCardapioDTO tipoCardapioDTO : tipoCardapioDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(tipoCardapioDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
