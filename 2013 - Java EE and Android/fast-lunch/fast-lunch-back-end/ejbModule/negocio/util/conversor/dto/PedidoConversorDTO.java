package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import constantes.EOrigemPedido;
import dto.PedidoDTO;
import entidade.Pedido;

public abstract class PedidoConversorDTO {


	
	public static Pedido converterDTOEmEntidadeEspecial(PedidoDTO pedidoDTO)
	{	

		Pedido pedido = new Pedido();
		if(pedidoDTO != null)
		{
			pedido.setEstabelecimento(EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getEstabelecimento()));
			pedido.setFuncionario(FuncionarioConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getFuncionario()));
			pedido.setCliente(ClienteConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getCliente()));
			pedido.setDataPedido(pedidoDTO.getDataPedido());
			pedido.setValorTotal(pedidoDTO.getValorTotal());
			pedido.setOrigemPedido(pedidoDTO.getOrigemPedido().name());
			pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
			/*pedido.setStatusPedidos(StatusPedidoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getStatusPedidos()));
			pedido.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getTipoPagamentos()));*/
		}
		return pedido;
	}
	
	public static Pedido converterDTOEmEntidadeEspecialComId(PedidoDTO pedidoDTO)
	{	

		Pedido pedido = new Pedido();
		if(pedidoDTO != null)
		{
			pedido.setIdPedido(pedidoDTO.getIdPedido());
			pedido.setEstabelecimento(EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getEstabelecimento()));
			pedido.setFuncionario(FuncionarioConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getFuncionario()));
			pedido.setCliente(ClienteConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getCliente()));
			pedido.setDataPedido(pedidoDTO.getDataPedido());
			pedido.setValorTotal(pedidoDTO.getValorTotal());
			pedido.setOrigemPedido(pedidoDTO.getOrigemPedido().name());
			pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
			/*pedido.setStatusPedidos(StatusPedidoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getStatusPedidos()));
			pedido.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getTipoPagamentos()));*/
		}
		return pedido;
	}
	
	public static PedidoDTO converterEntidadeEmDTOEspecialComId(Pedido pedido)
	{	
		PedidoDTO pedidoDTO = new PedidoDTO();
		if(pedido != null)
		{
			pedidoDTO.setEstabelecimento(EstabelecimentoConversorDTO.
					converterEntidadeEmDTO(pedido.getEstabelecimento()));
			pedidoDTO.setFuncionario(FuncionarioConversorDTO.
					converterEntidadeEmDTO(pedido.getFuncionario()));
			pedidoDTO.setCliente(ClienteConversorDTO.
					converterEntidadeEmDTO(pedido.getCliente()));
			pedidoDTO.setDataPedido(pedido.getDataPedido());
			pedidoDTO.setValorTotal(pedido.getValorTotal());
			pedidoDTO.setOrigemPedido(EOrigemPedido.valueOf(pedido.getOrigemPedido()));
			pedidoDTO.setNumeroPedido(pedido.getNumeroPedido());
			/*pedidoDTO.setStatusPedidos(StatusPedidoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getStatusPedidos()));
			pedidoDTO.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getTipoPagamentos()));
			pedidoDTO.setIdPedido(pedido.getIdPedido());*/
		}
		return pedidoDTO;
	}
	
	public static PedidoDTO converterEntidadeEmDTOEspecial(Pedido pedido)
	{	
		PedidoDTO pedidoDTO = new PedidoDTO();
		if(pedido != null)
		{
			pedidoDTO.setEstabelecimento(EstabelecimentoConversorDTO.
					converterEntidadeEmDTO(pedido.getEstabelecimento()));
			pedidoDTO.setFuncionario(FuncionarioConversorDTO.
					converterEntidadeEmDTO(pedido.getFuncionario()));
			pedidoDTO.setCliente(ClienteConversorDTO.
					converterEntidadeEmDTO(pedido.getCliente()));
			pedidoDTO.setDataPedido(pedido.getDataPedido());
			pedidoDTO.setValorTotal(pedido.getValorTotal());
			pedidoDTO.setOrigemPedido(EOrigemPedido.valueOf(pedido.getOrigemPedido()));
			pedidoDTO.setNumeroPedido(pedido.getNumeroPedido());
			/*pedidoDTO.setStatusPedidos(StatusPedidoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getStatusPedidos()));
			pedidoDTO.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getTipoPagamentos()));*/
		}
		return pedidoDTO;
	}
	public static Pedido converterDTOEmEntidade(PedidoDTO pedidoDTO)
	{	
		Pedido pedido = new Pedido();
		if(pedidoDTO != null)
		{
			pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
			pedido.setEstabelecimento(EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getEstabelecimento()));
			pedido.setFuncionario(FuncionarioConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getFuncionario()));
			pedido.setCliente(ClienteConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getCliente()));
			pedido.setDataPedido(pedidoDTO.getDataPedido());
			pedido.setValorTotal(pedidoDTO.getValorTotal());
			pedido.setOrigemPedido(pedidoDTO.getOrigemPedido().name());
			pedido.setStatusPedidos(StatusPedidoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getStatusPedidos()));
			pedido.setItemPedidos(ItemPedidoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getItemPedidos()));
			pedido.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getTipoPagamentos()));
		}
		return pedido;

	}
	public static Pedido converterDTOEmEntidadeComId(PedidoDTO pedidoDTO)
	{	

		Pedido pedido = new Pedido();
		if(pedidoDTO != null)
		{
			pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
			pedido.setEstabelecimento(EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getEstabelecimento()));
			pedido.setFuncionario(FuncionarioConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getFuncionario()));
			pedido.setCliente(ClienteConversorDTO.
					converterDTOEmEntidadeComId(pedidoDTO.getCliente()));
			pedido.setDataPedido(pedidoDTO.getDataPedido());
			pedido.setValorTotal(pedidoDTO.getValorTotal());
			pedido.setOrigemPedido(pedidoDTO.getOrigemPedido().name());
			pedido.setStatusPedidos(StatusPedidoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getStatusPedidos()));
			pedido.setItemPedidos(ItemPedidoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getItemPedidos()));
			pedido.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterDTOsListEmEntidadesSet(pedidoDTO.getTipoPagamentos()));
			pedido.setIdPedido(pedidoDTO.getIdPedido());
		}
		return pedido;
	}

	public static PedidoDTO converterEntidadeEmDTO(Pedido pedido)
	{	
		PedidoDTO pedidoDTO = new PedidoDTO();
		if(pedido != null)
		{
			pedidoDTO.setNumeroPedido(pedido.getNumeroPedido());
			pedidoDTO.setEstabelecimento(EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(pedido.getEstabelecimento()));
			pedidoDTO.setFuncionario(FuncionarioConversorDTO.
					converterEntidadeEmDTOComId(pedido.getFuncionario()));
			pedidoDTO.setCliente(ClienteConversorDTO.
					converterEntidadeEmDTOComId(pedido.getCliente()));
			pedidoDTO.setDataPedido(pedido.getDataPedido());
			pedidoDTO.setValorTotal(pedido.getValorTotal());
			pedidoDTO.setOrigemPedido(EOrigemPedido.valueOf(pedido.getOrigemPedido()));
			pedidoDTO.setStatusPedidos(StatusPedidoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getStatusPedidos()));
			pedidoDTO.setItemPedidos(ItemPedidoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getItemPedidos()));
			pedidoDTO.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getTipoPagamentos()));
		}
		return pedidoDTO;
	}

	public static PedidoDTO converterEntidadeEmDTOComId(Pedido pedido)
	{	
		PedidoDTO pedidoDTO = new PedidoDTO();
		if(pedido != null)
		{
			pedidoDTO.setNumeroPedido(pedido.getNumeroPedido());
			pedidoDTO.setEstabelecimento(EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(pedido.getEstabelecimento()));
			pedidoDTO.setFuncionario(FuncionarioConversorDTO.
					converterEntidadeEmDTOComId(pedido.getFuncionario()));
			pedidoDTO.setCliente(ClienteConversorDTO.
					converterEntidadeEmDTOComId(pedido.getCliente()));
			pedidoDTO.setDataPedido(pedido.getDataPedido());
			pedidoDTO.setValorTotal(pedido.getValorTotal());
			pedidoDTO.setOrigemPedido(EOrigemPedido.valueOf(pedido.getOrigemPedido()));
			pedidoDTO.setStatusPedidos(StatusPedidoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getStatusPedidos()));
			pedidoDTO.setItemPedidos(ItemPedidoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getItemPedidos()));
			pedidoDTO.setTipoPagamentos(TipoPagamentoConversorDTO.
					converterEntidadesSetEmDTOsList(pedido.getTipoPagamentos()));
			pedidoDTO.setIdPedido(pedido.getIdPedido());
		}
		return pedidoDTO;
	}

	public static List<PedidoDTO> converterEntidadesSetEmDTOsList(Set<Pedido> pedidosSet)
	{
		List<PedidoDTO> pedidosDTOList = new ArrayList<PedidoDTO>();
		if(pedidosSet != null)
		{
			for(Pedido pedido : pedidosSet)
			{
				pedidosDTOList.add(converterEntidadeEmDTO(pedido));
			}
		}
		return pedidosDTOList;
	}

	public static Set<Pedido> converterDTOsListEmEntidadesSet(List<PedidoDTO> pedidosDTOList)
	{
		Set<Pedido> pedidosSet = new HashSet<Pedido>(0);
		if(pedidosDTOList != null)
		{
			for(PedidoDTO pedidoDTO : pedidosDTOList)
			{
				pedidosSet.add(converterDTOEmEntidadeComId(pedidoDTO));
			}
		}
		return pedidosSet;
	}

	public static List<PedidoDTO> converterEntidadesListEmDTOsList(List<Pedido> pedidosList)
	{
		List<PedidoDTO> pedidosDTOList = new ArrayList<PedidoDTO>();
		if(pedidosList != null)
		{
			for(Pedido pedido : pedidosList)
			{
				pedidosDTOList.add(converterEntidadeEmDTOComId(pedido));
			}
		}
		return pedidosDTOList;
	}
}
