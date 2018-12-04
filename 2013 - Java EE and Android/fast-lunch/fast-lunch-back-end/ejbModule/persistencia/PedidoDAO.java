package persistencia;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import constantes.EStatusPedido;
import entidade.ItemPedido;
import entidade.Pedido;
import entidade.StatusPedido;

@Stateless
public class PedidoDAO extends GenericDAO<Pedido> {

	public PedidoDAO() {
		super(Pedido.class);
	}
	
	public void remover(Pedido pedido)
	{
		super.remover(pedido.getIdPedido(), Pedido.class);
	}
	
	public long salvarPedido(Pedido entidade, Set<ItemPedido> itemPedidos) {
		long numeroPedido = 0;
		Pedido p = buscarPedidoPeloNumero(entidade.getEstabelecimento().getIdEstabelecimento()); 
		System.out.println("*** Pedido: " + p);

		if(p != null)
		{
			System.out.println("Não é o primeiro, numero Pedido: " + p.getNumeroPedido());
			if(p.getNumeroPedido() != null)
			{
				numeroPedido = (p.getNumeroPedido() + 1);
			}
		}
		else
		{
			System.out.println("É o primeiro...");
			numeroPedido = 1;
		}
		
		System.out.println("Numero do Pedido: " + numeroPedido);
		entidade.setNumeroPedido(numeroPedido);
		
		entidade.setItemPedidos(null);
		getEm().merge(entidade);
		Pedido pedidoPersistido = buscarPedidoPeloNumero(entidade.getEstabelecimento().getIdEstabelecimento());
		System.out.println("Persistiu!!! Numero: " + numeroPedido);
		System.out.println("Estabelecimento: " + entidade.getEstabelecimento().getIdEstabelecimento());
		System.out.println("Pedido persistido: " + pedidoPersistido);
		System.out.println("ID do Pedido persistido: " + pedidoPersistido.getIdPedido());
		for(ItemPedido ip : itemPedidos)
		{
			ip.setPedido(pedidoPersistido);
			getEm().persist(ip);
		}
		
		StatusPedido statusPedido = new StatusPedido();
		statusPedido.setDataHora(new Date());
		statusPedido.setPedido(pedidoPersistido);
		statusPedido.setStatusPedido(EStatusPedido.COZINHA.name());
		getEm().persist(statusPedido);

		return numeroPedido;
	}
	
	public List<Pedido> buscarPedidosPeloCnpjEstabelecimento(String cnpj)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("cnpj", cnpj);
		
		return super.buscarResultados(Pedido.BUSCA_PEDIDOS_POR_ESTABELECIMENTO, parametros);
	}
	
	public List<Pedido> buscarPedidosPeloIdEstabelecimento(Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", id_estabelecimento);
		
		return super.buscarResultados(Pedido.BUSCA_PEDIDOS_POR_ESTABELECIMENTO_ID, parametros);
	}
	
	public List<Pedido> buscarPedidosPeloIdFuncionario(Long id_funcionario)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idFuncionario", id_funcionario);
		
		return super.buscarResultados(Pedido.BUSCA_PEDIDOS_POR_FUNCIONARIO, parametros);
	}
	
	public List<Pedido> buscarPedidosPeloCpfCliente(String cpf, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("cpf", cpf);
		parametros.put("idEstabelecimento", id_estabelecimento);
		
		return super.buscarResultados(Pedido.BUSCA_PEDIDOS_POR_CLIENTE, parametros);
	}
	
	public List<Pedido> buscarPedidosPelaOrigem(String origem, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("origem", origem);
		parametros.put("idEstabelecimento", id_estabelecimento);
		
		return super.buscarResultados(Pedido.BUSCA_PEDIDOS_POR_ORIGEM, parametros);
	}
	
	public Pedido buscarPedidoPeloNumero(Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", id_estabelecimento);
		
		return super.buscarUmResultado(Pedido.BUSCA_PEDIDO_POR_NUMERO, parametros);
	}
}
