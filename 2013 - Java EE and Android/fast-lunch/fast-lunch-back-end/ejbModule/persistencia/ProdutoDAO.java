package persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Produto;

@Stateless
public class ProdutoDAO extends GenericDAO<Produto> {

	public ProdutoDAO() {
		super(Produto.class);
	}
	
	public void remover(Produto produto)
	{
		super.remover(produto.getIdProduto(), Produto.class);
	}
	
	public Produto buscarProdutoPeloNome(String nome, Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nome", nome);
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarUmResultado(Produto.BUSCA_PRODUTO_POR_NOME, parametros);
	}
}
