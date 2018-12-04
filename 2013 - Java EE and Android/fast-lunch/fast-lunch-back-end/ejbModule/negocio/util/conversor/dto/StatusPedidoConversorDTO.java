package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import constantes.EStatusPedido;
import dto.StatusPedidoDTO;
import entidade.StatusPedido;

public abstract class StatusPedidoConversorDTO {

	public static StatusPedido converterDTOEmEntidade(StatusPedidoDTO statusPedidoDTO)
	{	
		StatusPedido statusPedido = new StatusPedido();
		if(statusPedidoDTO != null)
		{
			statusPedido.setPedido(PedidoConversorDTO.
					converterDTOEmEntidadeEspecial(statusPedidoDTO.getPedido()));
			statusPedido.setStatusPedido(statusPedidoDTO.getStatusPedido().name());
			statusPedido.setDataHora(statusPedidoDTO.getDataHora());
		}
		return statusPedido;
	}

	public static StatusPedido converterDTOEmEntidadeComId(StatusPedidoDTO statusPedidoDTO)
	{	
		StatusPedido statusPedido = new StatusPedido();
		if(statusPedidoDTO != null)
		{
			statusPedido.setPedido(PedidoConversorDTO.
					converterDTOEmEntidadeEspecialComId(statusPedidoDTO.getPedido()));
			statusPedido.setStatusPedido(statusPedidoDTO.getStatusPedido().name());
			statusPedido.setDataHora(statusPedidoDTO.getDataHora());
			statusPedido.setIdStatusPedido(statusPedidoDTO.getIdStatusPedido());
		}
		return statusPedido;
	}

	public static StatusPedidoDTO converterEntidadeEmDTO(StatusPedido statusPedido)
	{	
		StatusPedidoDTO statusPedidoDTO = new StatusPedidoDTO();
		if(statusPedido != null)
		{
			statusPedidoDTO.setPedido(PedidoConversorDTO.
					converterEntidadeEmDTOEspecial(statusPedido.getPedido()));
			statusPedidoDTO.setStatusPedido(EStatusPedido.valueOf(statusPedido.getStatusPedido()));
			statusPedidoDTO.setDataHora(statusPedido.getDataHora());
		}
		return statusPedidoDTO;
	}

	public static StatusPedidoDTO converterEntidadeEmDTOComId(StatusPedido statusPedido)
	{	
		StatusPedidoDTO statusPedidoDTO = new StatusPedidoDTO();
		if(statusPedido != null)
		{
			statusPedidoDTO.setPedido(PedidoConversorDTO.
					converterEntidadeEmDTOEspecialComId(statusPedido.getPedido()));
			statusPedidoDTO.setStatusPedido(EStatusPedido.valueOf(statusPedido.getStatusPedido()));
			statusPedidoDTO.setDataHora(statusPedido.getDataHora());
			statusPedidoDTO.setIdStatusPedido(statusPedido.getIdStatusPedido());
		}
		return statusPedidoDTO;
	}

	public static List<StatusPedidoDTO> converterEntidadesSetEmDTOsList(Set<StatusPedido> statusPedidosSet)
	{
		List<StatusPedidoDTO> statusPedidosDTOList = new ArrayList<StatusPedidoDTO>();
		if(statusPedidosSet != null)
		{
			for(StatusPedido statusPedido : statusPedidosSet)
			{
				statusPedidosDTOList.add(converterEntidadeEmDTOComId(statusPedido));
			}
		}
		return statusPedidosDTOList;
	}

	public static Set<StatusPedido> converterDTOsListEmEntidadesSet(List<StatusPedidoDTO> statusPedidosDTOList)
	{
		Set<StatusPedido> statusPedidosSet = new HashSet<StatusPedido>(0);
		if(statusPedidosDTOList != null)
		{
			for(StatusPedidoDTO statusPedidoDTO : statusPedidosDTOList)
			{
				statusPedidosSet.add(converterDTOEmEntidadeComId(statusPedidoDTO));
			}
		}
		return statusPedidosSet;
	}

	public static List<StatusPedidoDTO> converterEntidadesListEmDTOsList(List<StatusPedido> statusPedidosList)
	{
		List<StatusPedidoDTO> statusPedidosDTOList = new ArrayList<StatusPedidoDTO>();
		if(statusPedidosList != null)
		{
			for(StatusPedido statusPedido : statusPedidosList)
			{
				statusPedidosDTOList.add(converterEntidadeEmDTOComId(statusPedido));
			}
		}
		return statusPedidosDTOList;
	}
}
