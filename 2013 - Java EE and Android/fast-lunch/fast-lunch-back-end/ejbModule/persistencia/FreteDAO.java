package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Frete;

@Stateless
public class FreteDAO extends GenericDAO<Frete> {

	public FreteDAO() {
		super(Frete.class);
	}
	
	public void remover(Frete frete)
	{
		super.remover(frete.getIdFrete(), Frete.class);
	}

	public Frete buscarFretePorBairro(String bairro, Long idEstabelecimento) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("bairro", bairro);
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarUmResultado(Frete.BUSCA_FRETE_POR_BAIRRO, parametros);
	}
	
	public List<Frete> buscarFretesPeloEstabelecimento(Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarResultados(Frete.BUSCA_FRETES_POR_ESTABELECIMENTO, parametros);
	}
}
