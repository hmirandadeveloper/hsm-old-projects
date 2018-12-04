package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.CardapioProduto;

@Stateless
public class CardapioProdutoDAO extends GenericDAO<CardapioProduto> {

	public CardapioProdutoDAO() {
		super(CardapioProduto.class);
	}
	
	public void remover(CardapioProduto cardapioProduto)
	{
		super.remover(cardapioProduto.getIdcardapioProduto(), CardapioProduto.class);
	}
	
	@Override
	public void salvar(CardapioProduto entidade) {
		
		if(entidade.getProduto().getIdProduto() == null)
		{
			super.salvar(entidade);
		}
		else
		{
			super.atualizar(entidade);
		}
	}
	
	public CardapioProduto buscarCardapioProdutoPeloNomeProduto(String nomeProduto, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nomeProduto", nomeProduto);
		parametros.put("idEstabelecimento", id_estabelecimento);
				
		return super.buscarUmResultado(CardapioProduto.BUSCA_PRODUTO_POR_NOME, parametros);
	}
	
	public List<CardapioProduto> buscarCardapioProdutosPeloNomeProduto(String nomeProduto, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nomeProduto", nomeProduto);
		parametros.put("idEstabelecimento", id_estabelecimento);
		
		return super.buscarResultados(CardapioProduto.BUSCA_PRODUTO_POR_NOME, parametros);
	}
	
	public CardapioProduto buscarCardapioProdutoPeloNomeProdutoECardapio(String nomeProduto, String nomeCardapio, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nomeProduto", nomeProduto);
		parametros.put("nomeCardapio", nomeCardapio);
		parametros.put("idEstabelecimento", id_estabelecimento);
				
		return super.buscarUmResultado(CardapioProduto.BUSCA_PRODUTO_POR_NOME_E_CARDAPIO, parametros);
	}
	
	public List<CardapioProduto> buscarCardapioProdutoPeloCardapio(String nomeCardapio, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nomeCardapio", nomeCardapio);
		parametros.put("idEstabelecimento", id_estabelecimento);
				
		return super.buscarResultados(CardapioProduto.BUSCA_PRODUTO_POR_CARDAPIO, parametros);
	}
	
	public List<CardapioProduto> buscarCardapioProdutosPeloEstabelecimento(Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", id_estabelecimento);
		
		return super.buscarResultados(CardapioProduto.BUSCA_PRODUTOS_POR_ESTABELECIMENTO, parametros);
	}
}
