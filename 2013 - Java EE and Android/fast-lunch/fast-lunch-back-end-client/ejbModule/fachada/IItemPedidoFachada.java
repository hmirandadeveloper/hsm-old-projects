package fachada;

import java.util.List;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.ValorInvalidoException;
import dto.ItemPedidoDTO;

public interface IItemPedidoFachada {
	public abstract void salvar(ItemPedidoDTO itemPedidoDTO) throws 
	EntidadeAtributoIncompletoException, ValorInvalidoException;
	public abstract void remover(Long idItemPedido) throws 
	EntidadeInexistenteException;
	public abstract ItemPedidoDTO atualizar(ItemPedidoDTO itemPedidoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract ItemPedidoDTO buscar(long itemPedidoId);
	public abstract List<ItemPedidoDTO> buscarTodos();
}
