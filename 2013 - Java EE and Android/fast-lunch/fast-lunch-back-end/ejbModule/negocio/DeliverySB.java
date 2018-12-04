package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.DeliveryConversorDTO;
import negocio.util.validador.entidade.DeliveryAtributoValidador;

import persistencia.DeliveryDAO;

import dto.DeliveryDTO;
import entidade.Delivery;
import fachada.IDeliveryFachada;

@Stateless
@Remote(IDeliveryFachada.class)
public class DeliverySB implements IDeliveryFachada{
	
	@EJB
	DeliveryDAO deliveryDAO;
	
	@Override
	public void salvar(DeliveryDTO deliveryDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		
		if(DeliveryAtributoValidador.validarAtributosPreenchidosEntidade(deliveryDTO))
		{
			if(buscar(deliveryDTO.getIdDelivery()) == null)
			{
				this.deliveryDAO.salvar(DeliveryConversorDTO.converterDTOEmEntidade(deliveryDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(Delivery.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Delivery.class);
		}
	}

	@Override
	public void remover(long deliveryId) throws EntidadeInexistenteException {
		if(buscar(deliveryId) != null)
		{
			this.deliveryDAO.remover(gerarEntidadePorId(deliveryId));
		}
		else
		{
			throw new EntidadeInexistenteException(Delivery.class);
		}
	}

	@Override
	public DeliveryDTO atualizar(DeliveryDTO deliveryDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		
		if(DeliveryAtributoValidador.validarAtributosPreenchidosEntidade(deliveryDTO))
		{
			if(buscar(deliveryDTO.getIdDelivery()) != null)
			{
				return DeliveryConversorDTO.converterEntidadeEmDTO(
						this.deliveryDAO.atualizar(
								DeliveryConversorDTO.converterDTOEmEntidadeComId(deliveryDTO))
						);
			}
			else
			{
				throw new EntidadeInexistenteException(Delivery.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Delivery.class);
		}
	}

	@Override
	public DeliveryDTO buscar(long deliveryId) {
		return DeliveryConversorDTO.converterEntidadeEmDTOComId(
				this.deliveryDAO.buscar(deliveryId));
	}

	@Override
	public List<DeliveryDTO> buscarTodos() {
		return DeliveryConversorDTO.converterEntidadesListEmDTOsList(
					this.deliveryDAO.buscarTodos()
				);
	}
	
	private Delivery gerarEntidadePorId(long deliveryId)
	{
		Delivery delivery = new Delivery();
		delivery.setIdDelivery(deliveryId);
		return delivery;
	}

}
