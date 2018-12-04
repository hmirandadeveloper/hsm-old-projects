package fachada;

import java.util.List;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;

import dto.DeliveryDTO;

public interface IDeliveryFachada {
	public abstract void salvar(DeliveryDTO deliveryDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long deliveryId) throws 
	EntidadeInexistenteException;
	public abstract DeliveryDTO atualizar(DeliveryDTO deliveryDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract DeliveryDTO buscar(long deliveryId);
	public abstract List<DeliveryDTO> buscarTodos();
}
