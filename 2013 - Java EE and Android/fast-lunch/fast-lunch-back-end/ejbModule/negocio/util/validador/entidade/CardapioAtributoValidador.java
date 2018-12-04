package negocio.util.validador.entidade;

import java.util.List;

import dto.CardapioDTO;

public abstract class CardapioAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(CardapioDTO cardapioDTO)
	{
		boolean atributosCorretos = true;

		if(cardapioDTO == null)
		{
			return false;
		}
		
		if(cardapioDTO.getNome() == null || 
				cardapioDTO.getNome().equals(""))
		{
			atributosCorretos = false;
		}
		if(!TipoCardapioAtributoValidador.
				validarAtributosPreenchidosEntidade(
						cardapioDTO.getTipoCardapio()))
		{
			atributosCorretos = false;
		}
		if(cardapioDTO.getValidade() == null)
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<CardapioDTO> cardapioDTOs)
	{
		boolean atributosCorretos = true;
		
		for(CardapioDTO cardapioDTO : cardapioDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(cardapioDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
