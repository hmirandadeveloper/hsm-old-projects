package negocio.util.validador.entidade;

import java.util.List;

import dto.ItemPedidoDTO;

public abstract class ItemPedidoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(ItemPedidoDTO itemPedidoDTO)
	{
		boolean atributosCorretos = true;
		
		if(itemPedidoDTO == null)
		{
			return false;
		}
		
		if(!PedidoAtributoValidador.
				validarAtributosPreenchidosEntidade(itemPedidoDTO.getPedido()))
		{
			atributosCorretos = false;
		}
		if(!CardapioProdutoAtributoValidador.
				validarAtributosPreenchidosEntidade(itemPedidoDTO.getCardapioProduto()))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<ItemPedidoDTO> itemPedidoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(ItemPedidoDTO itemPedidoDTO : itemPedidoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(itemPedidoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
