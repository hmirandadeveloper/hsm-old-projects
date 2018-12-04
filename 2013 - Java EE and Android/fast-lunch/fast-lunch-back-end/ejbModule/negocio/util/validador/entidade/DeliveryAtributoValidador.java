package negocio.util.validador.entidade;

import java.util.List;

import dto.DeliveryDTO;

public abstract class DeliveryAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(DeliveryDTO deliveryDTO)
	{
		boolean atributosCorretos = true;

		if(deliveryDTO == null)
		{
			return false;
		}
		
		if(!EnderecoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						deliveryDTO.getEndereco()))
		{
			atributosCorretos = false;
		}
		if(!PedidoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						deliveryDTO.getPedido()))
		{
			atributosCorretos = false;
		}
		if(!FreteAtributoValidador.
				validarAtributosPreenchidosEntidade(
						deliveryDTO.getFrete()))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<DeliveryDTO> deliveryDTOs)
	{
		boolean atributosCorretos = true;
		
		for(DeliveryDTO deliveryDTO : deliveryDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(deliveryDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
