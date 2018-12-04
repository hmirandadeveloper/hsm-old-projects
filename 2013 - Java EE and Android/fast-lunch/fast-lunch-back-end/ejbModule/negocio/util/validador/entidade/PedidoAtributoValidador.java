package negocio.util.validador.entidade;

import java.util.List;

import dto.PedidoDTO;

public abstract class PedidoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(PedidoDTO pedidoDTO)
	{
		boolean atributosCorretos = true;
		
		System.out.println("Validadção de Atributos de Pedido: " + pedidoDTO);
		
		if(pedidoDTO == null)
		{
			return false;
		}
		
		if(!ClienteAtributoValidador.
				validarAtributosPreenchidosEntidade(pedidoDTO.getCliente()))
		{
			atributosCorretos = false;
		}
		if(!FuncionarioAtributoValidador.
				validarAtributosPreenchidosEntidade(pedidoDTO.getFuncionario()))
		{
			atributosCorretos = false;
		}
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(pedidoDTO.getEstabelecimento()))
		{
			atributosCorretos = false;
		}
		if(pedidoDTO.getValorTotal() <= 0)
		{
			atributosCorretos = false;
		}
		if(pedidoDTO.getOrigemPedido() == null)
		{
			atributosCorretos = false;
		}
		if(pedidoDTO.getTipoPagamentos().size() <= 0 )
		{
			atributosCorretos = false;
		}
		
		System.out.println("Resultado da validação de atributos do Pedido: " + atributosCorretos);
		
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<PedidoDTO> pedidoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(PedidoDTO pedidoDTO : pedidoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(pedidoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
