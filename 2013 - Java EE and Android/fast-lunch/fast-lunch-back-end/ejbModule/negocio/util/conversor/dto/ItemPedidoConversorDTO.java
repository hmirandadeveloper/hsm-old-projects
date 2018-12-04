package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.ItemPedidoDTO;
import entidade.ItemPedido;

public abstract class ItemPedidoConversorDTO {

	public static ItemPedido converterDTOEmEntidade(ItemPedidoDTO itemPedidoDTO)
	{	
		ItemPedido itemPedido = new ItemPedido();
		if(itemPedidoDTO != null)
		{

			itemPedido.setCardapioProduto(CardapioProdutoConversorDTO.
					converterDTOEmEntidadeComId(itemPedidoDTO.getCardapioProduto()));
			itemPedido.setPedido(null);
			itemPedido.setPedido(PedidoConversorDTO.converterDTOEmEntidadeEspecial(itemPedidoDTO.getPedido()));
			itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
			System.out.println("Conversão ItemPedido - Concluída!!!");
		}
		return itemPedido;
	}

	public static ItemPedido converterDTOEmEntidadeComId(ItemPedidoDTO itemPedidoDTO)
	{	
		ItemPedido itemPedido = new ItemPedido();
		if(itemPedidoDTO != null)
		{
			itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
			itemPedido.setCardapioProduto(CardapioProdutoConversorDTO.
					converterDTOEmEntidade(itemPedidoDTO.getCardapioProduto()));
			itemPedido.setPedido(PedidoConversorDTO.converterDTOEmEntidadeEspecialComId((itemPedidoDTO.getPedido())));
			itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
		}
		return itemPedido;
	}

	public static ItemPedidoDTO converterEntidadeEmDTO(ItemPedido itemPedido)
	{	
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
		if(itemPedido != null)
		{
			itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
			itemPedidoDTO.setCardapioProduto(CardapioProdutoConversorDTO.
					converterEntidadeEmDTO(itemPedido.getCardapioProduto()));
			itemPedidoDTO.setPedido(PedidoConversorDTO.converterEntidadeEmDTOEspecial(itemPedido.getPedido()));
			itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
		}
		return itemPedidoDTO;
	}

	public static ItemPedidoDTO converterEntidadeEmDTOComId(ItemPedido itemPedido)
	{	
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
		if(itemPedido != null)
		{
			itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
			itemPedidoDTO.setCardapioProduto(CardapioProdutoConversorDTO.
					converterEntidadeEmDTO(itemPedido.getCardapioProduto()));
			itemPedidoDTO.setPedido(PedidoConversorDTO.converterEntidadeEmDTOEspecialComId(itemPedido.getPedido()));
			itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
		}
		return itemPedidoDTO;
	}

	public static List<ItemPedidoDTO> converterEntidadesSetEmDTOsList(Set<ItemPedido> itemPedidosSet)
	{
		List<ItemPedidoDTO> itemPedidosDTOList = new ArrayList<ItemPedidoDTO>();
		if(itemPedidosSet != null)
		{
			for(ItemPedido itemPedido : itemPedidosSet)
			{
				itemPedidosDTOList.add(converterEntidadeEmDTO(itemPedido));
			}
		}
		return itemPedidosDTOList;
	}

	public static Set<ItemPedido> converterDTOsListEmEntidadesSet(List<ItemPedidoDTO> itemPedidosDTOList)
	{
		Set<ItemPedido> itemPedidosSet = new HashSet<ItemPedido>(0);
		if(itemPedidosDTOList != null)
		{
			for(ItemPedidoDTO itemPedidoDTO : itemPedidosDTOList)
			{
				itemPedidosSet.add(converterDTOEmEntidade(itemPedidoDTO));
			}
		}
		return itemPedidosSet;
	}

	public static List<ItemPedidoDTO> converterEntidadesListEmDTOsList(List<ItemPedido> itemPedidosList)
	{
		List<ItemPedidoDTO> itemPedidosDTOList = new ArrayList<ItemPedidoDTO>();
		if(itemPedidosList != null)
		{
			for(ItemPedido itemPedido : itemPedidosList)
			{
				itemPedidosDTOList.add(converterEntidadeEmDTOComId(itemPedido));
			}
		}
		return itemPedidosDTOList;
	}
}
