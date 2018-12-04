package persistencia;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.ItemPedido;

@Stateless
public class ItemPedidoDAO extends GenericDAO<ItemPedido> {

	public ItemPedidoDAO() {
		super(ItemPedido.class);
	}
	
	public void remover(ItemPedido itemPedido)
	{
		super.remover(itemPedido.getIdItemPedido(), ItemPedido.class);
	}
}
