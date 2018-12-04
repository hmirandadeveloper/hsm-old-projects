package negocio.util.validador.entidade;

import java.util.List;

import dto.StatusPedidoDTO;

public abstract class StatusPedidoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(StatusPedidoDTO statusPedidoDTO)
	{
		boolean atributosCorretos = true;

		if(statusPedidoDTO == null)
		{
			return false;
		}
		
		if(!PedidoAtributoValidador.
				validarAtributosPreenchidosEntidade(statusPedidoDTO.getPedido()))
		{
			atributosCorretos = false;
		}
		if(statusPedidoDTO.getStatusPedido() == null)
		{
			atributosCorretos = false;
		}
		if(statusPedidoDTO.getDataHora() == null)
		{
			atributosCorretos = false;
		}
		
		
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<StatusPedidoDTO> statusPedidoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(StatusPedidoDTO statusPedidoDTO : statusPedidoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(statusPedidoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
