package persistencia;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Delivery;

@Stateless
public class DeliveryDAO extends GenericDAO<Delivery> {

	public DeliveryDAO() {
		super(Delivery.class);
	}
	
	public void remover(Delivery delivery)
	{
		super.remover(delivery.getIdDelivery(), Delivery.class);
	}
}
