package persistencia;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Cardapio;

@Stateless
public class CardapioDAO extends GenericDAO<Cardapio> {

	public CardapioDAO() {
		super(Cardapio.class);
	}
	
	public void remover(Cardapio cardapio)
	{
		super.remover(cardapio.getIdCardapio(), Cardapio.class);
	}
	
	public Cardapio buscarCardapioPeloNome(String nome, Long idEstabelecimento, Date dataAtual)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nome", nome);
		parametros.put("idEstabelecimento", idEstabelecimento);
		parametros.put("dataAtual", dataAtual);
		
		return super.buscarUmResultado(Cardapio.BUSCA_CARDAPIO_POR_NOME, parametros);
	}
	
	public List<Cardapio> buscarCardapiosPeloEstabelecimento(Long idEstabelecimento, Date dataAtual)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		parametros.put("dataAtual", dataAtual);
		
		return super.buscarResultados(Cardapio.BUSCA_CARDAPIOS_POR_ESTABELECIMENTO, parametros);
	}
}
