package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.TipoPagamento;

@Stateless
public class TipoPagamentoDAO extends GenericDAO<TipoPagamento> {

	public TipoPagamentoDAO() {
		super(TipoPagamento.class);
	}
	
	public void remover(TipoPagamento tipoPagamento)
	{
		super.remover(tipoPagamento.getIdTipoPagamento(), TipoPagamento.class);
	}
	
	public TipoPagamento buscarTipoPagamentoPelaDescricao(String descricao, Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", descricao);
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarUmResultado(TipoPagamento.BUSCA_TIPO_PAGAMENTO_POR_DESCRICAO, parametros);
	}
	
	public List<TipoPagamento> buscarTipoPagamentosPeloEstabelecimento(Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarResultados(TipoPagamento.BUSCA_TIPO_PAGAMENTOS_POR_ESTABELECIMENTO, parametros);
	}
}
