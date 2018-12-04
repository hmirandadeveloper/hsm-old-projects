package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.TipoCardapio;

@Stateless
public class TipoCardapioDAO extends GenericDAO<TipoCardapio> {

	public TipoCardapioDAO() {
		super(TipoCardapio.class);
	}
	
	public void remover(TipoCardapio tipoCardapio)
	{
		super.remover(tipoCardapio.getIdTipoCardapio(), TipoCardapio.class);
	}
	
	public TipoCardapio buscarTipoCardapioPeloNome(String nome, Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nome", nome);
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarUmResultado(TipoCardapio.BUSCA_TIPO_CARDAPIO_POR_NOME, parametros);
	}
	
	public List<TipoCardapio> buscarTipoCardapiosPeloEstabelecimento(Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarResultados(TipoCardapio.BUSCA_TIPO_CARDAPIOS_POR_ESTABELECIMENTO, parametros);
	}
}
