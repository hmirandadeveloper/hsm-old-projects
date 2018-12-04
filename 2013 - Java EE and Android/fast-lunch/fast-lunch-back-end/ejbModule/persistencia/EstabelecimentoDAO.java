package persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Estabelecimento;

@Stateless
public class EstabelecimentoDAO extends GenericDAO<Estabelecimento> {

	public EstabelecimentoDAO() {
		super(Estabelecimento.class);
	}
	
	public void remover(Estabelecimento estabelecimento)
	{
		super.remover(estabelecimento.getIdEstabelecimento(), Estabelecimento.class);
	}
	
	public Estabelecimento buscarEstabelecimentoPeloCnpj(String cnpj)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("cnpj", cnpj);
		
		return super.buscarUmResultado(Estabelecimento.BUSCA_ESTABELECIMENTO_POR_CNPJ, parametros);
	}
}
