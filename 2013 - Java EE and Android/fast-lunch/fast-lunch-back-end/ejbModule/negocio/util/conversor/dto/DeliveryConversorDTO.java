package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.DeliveryDTO;
import entidade.Delivery;

public abstract class DeliveryConversorDTO {

	public static Delivery converterDTOEmEntidade(DeliveryDTO deliveryDTO)
	{	

		Delivery delivery = new Delivery();
		if(deliveryDTO != null)
		{
			delivery.setPedido(PedidoConversorDTO.
					converterDTOEmEntidade(deliveryDTO.getPedido()));
			delivery.setFrete(FreteConversorDTO.
					converterDTOEmEntidade(deliveryDTO.getFrete()));
			delivery.setEndereco(EnderecoConversorDTO.
					converterDTOEmEntidade(deliveryDTO.getEndereco()));
		}
		return delivery;
	}

	public static Delivery converterDTOEmEntidadeComId(DeliveryDTO deliveryDTO)
	{	
		Delivery delivery = new Delivery();
		if(deliveryDTO != null)
		{
			delivery.setPedido(PedidoConversorDTO.
					converterDTOEmEntidade(deliveryDTO.getPedido()));
			delivery.setFrete(FreteConversorDTO.
					converterDTOEmEntidade(deliveryDTO.getFrete()));
			delivery.setEndereco(EnderecoConversorDTO.
					converterDTOEmEntidade(deliveryDTO.getEndereco()));
			delivery.setIdDelivery(deliveryDTO.getIdDelivery());
		}
		return delivery;
	}

	public static DeliveryDTO converterEntidadeEmDTO(Delivery delivery)
	{	
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		if(delivery != null)
		{
			deliveryDTO.setPedido(PedidoConversorDTO.
					converterEntidadeEmDTO(delivery.getPedido()));
			deliveryDTO.setFrete(FreteConversorDTO.
					converterEntidadeEmDTO(delivery.getFrete()));
			deliveryDTO.setEndereco(EnderecoConversorDTO.
					converterEntidadeEmDTO(delivery.getEndereco()));
		}
		return deliveryDTO;
	}

	public static DeliveryDTO converterEntidadeEmDTOComId(Delivery delivery)
	{	
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		if(delivery != null)
		{
			deliveryDTO.setPedido(PedidoConversorDTO.
					converterEntidadeEmDTO(delivery.getPedido()));
			deliveryDTO.setFrete(FreteConversorDTO.
					converterEntidadeEmDTO(delivery.getFrete()));
			deliveryDTO.setEndereco(EnderecoConversorDTO.
					converterEntidadeEmDTO(delivery.getEndereco()));
			deliveryDTO.setIdDelivery(delivery.getIdDelivery());
		}
		return deliveryDTO;
	}

	public static List<DeliveryDTO> converterEntidadesSetEmDTOsList(Set<Delivery> deliverysSet)
	{
		List<DeliveryDTO> deliverysDTOList = new ArrayList<DeliveryDTO>();
		if(deliverysSet != null)
		{
			for(Delivery delivery : deliverysSet)
			{
				deliverysDTOList.add(converterEntidadeEmDTO(delivery));
			}
		}
		return deliverysDTOList;
	}

	public static Set<Delivery> converterDTOsListEmEntidadesSet(List<DeliveryDTO> deliverysDTOList)
	{
		Set<Delivery> deliverysSet = new HashSet<Delivery>(0);
		if(deliverysDTOList != null)
		{
			for(DeliveryDTO deliveryDTO : deliverysDTOList)
			{
				deliverysSet.add(converterDTOEmEntidadeComId(deliveryDTO));
			}
		}
		return deliverysSet;
	}

	public static List<DeliveryDTO> converterEntidadesListEmDTOsList(List<Delivery> deliverysList)
	{
		List<DeliveryDTO> deliverysDTOList = new ArrayList<DeliveryDTO>();
		if(deliverysList != null)
		{
			for(Delivery delivery : deliverysList)
			{
				deliverysDTOList.add(converterEntidadeEmDTO(delivery));
			}
		}
		return deliverysDTOList;
	}
}
