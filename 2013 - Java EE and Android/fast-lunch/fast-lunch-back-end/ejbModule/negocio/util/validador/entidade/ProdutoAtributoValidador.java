package negocio.util.validador.entidade;

import java.util.List;

import dto.ProdutoDTO;

public abstract class ProdutoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(ProdutoDTO produtoDTO)
	{
		boolean atributosCorretos = true;

		if(produtoDTO == null)
		{
			return false;
		}
		
		if(produtoDTO.getNome() == null || 
				produtoDTO.getNome().equals(""))
		{
			atributosCorretos = false;
		}
		if(produtoDTO.getDescricao() == null || 
				produtoDTO.getDescricao().equals(""))
		{
			atributosCorretos = false;
		}
		if(!CardapioProdutoAtributoValidador.
				validarListEntidades(produtoDTO.getCardapioProdutos()) &&
				produtoDTO.getCardapioProdutos().size() <= 0)
		{
			atributosCorretos = false;
		}
		
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<ProdutoDTO> produtoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(ProdutoDTO produtoDTO : produtoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(produtoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
