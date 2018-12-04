package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.ValorInvalidoException;
import negocio.util.conversor.dto.ItemPedidoConversorDTO;
import negocio.util.validador.entidade.ItemPedidoAtributoValidador;
import persistencia.ItemPedidoDAO;
import dto.ItemPedidoDTO;
import entidade.ItemPedido;
import fachada.IItemPedidoFachada;

@Stateless
@Remote(IItemPedidoFachada.class)
public class ItemPedidoSB implements IItemPedidoFachada{
	
	@EJB
	ItemPedidoDAO itemPedidoDAO;
	
	@Override
	public void salvar(ItemPedidoDTO itemPedidoDTO) throws EntidadeAtributoIncompletoException, ValorInvalidoException {
		if(ItemPedidoAtributoValidador.validarAtributosPreenchidosEntidade(itemPedidoDTO))
		{
			if(itemPedidoDTO.getQuantidade() > 0)
			{
				this.itemPedidoDAO.salvar(ItemPedidoConversorDTO.converterDTOEmEntidade(itemPedidoDTO));
			}
			else
			{
				throw new ValorInvalidoException();
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(ItemPedido.class);
		}
	}

	@Override
	public void remover(Long idItemPedido) {
		
		this.itemPedidoDAO.remover(gerarEntidadePorId(idItemPedido));
	}

	@Override
	public ItemPedidoDTO atualizar(ItemPedidoDTO itemPedidoDTO) throws EntidadeAtributoIncompletoException {
		if(ItemPedidoAtributoValidador.validarAtributosPreenchidosEntidade(itemPedidoDTO))
		{
			return ItemPedidoConversorDTO.converterEntidadeEmDTO(
					this.itemPedidoDAO.atualizar(
							ItemPedidoConversorDTO.converterDTOEmEntidadeComId(itemPedidoDTO))
					);
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(ItemPedido.class);
		}
	}

	@Override
	public ItemPedidoDTO buscar(long itemPedidoId) {
		return ItemPedidoConversorDTO.converterEntidadeEmDTOComId(
				this.itemPedidoDAO.buscar(itemPedidoId));
	}

	@Override
	public List<ItemPedidoDTO> buscarTodos() {
		return ItemPedidoConversorDTO.converterEntidadesListEmDTOsList(
					this.itemPedidoDAO.buscarTodos()
				);
	}
	
	private ItemPedido gerarEntidadePorId(Long idItemPedido)
	{
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setIdItemPedido(idItemPedido);
		return itemPedido;
	}

}
