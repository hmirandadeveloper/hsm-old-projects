package persistencia;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.StatusPedido;

@Stateless
public class StatusPedidoDAO extends GenericDAO<StatusPedido> {

	public StatusPedidoDAO() {
		super(StatusPedido.class);
	}
	
	public void remover(StatusPedido statusPedido)
	{
		super.remover(statusPedido.getIdStatusPedido(), StatusPedido.class);
	}
}
