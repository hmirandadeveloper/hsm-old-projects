package negocio.util.validador.entidade;

import java.util.List;

import dto.CardapioProdutoDTO;

public abstract class CardapioProdutoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(CardapioProdutoDTO cardapioProdutoDTO)
	{
		boolean atributosCorretos = true;

		if(cardapioProdutoDTO == null)
		{
			return false;
		}
		
		if(!CardapioAtributoValidador.
				validarAtributosPreenchidosEntidade(
						cardapioProdutoDTO.getCardapio()))
		{
			atributosCorretos = false;
		}
		if(!ProdutoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						cardapioProdutoDTO.getProduto()))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<CardapioProdutoDTO> cardapioProdutoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(CardapioProdutoDTO cardapioProdutoDTO : cardapioProdutoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(cardapioProdutoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
