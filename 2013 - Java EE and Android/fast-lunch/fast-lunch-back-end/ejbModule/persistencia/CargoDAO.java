package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Cargo;

@Stateless
public class CargoDAO extends GenericDAO<Cargo> {

	public CargoDAO() {
		super(Cargo.class);
	}
	
	public void remover(Cargo cargo)
	{
		super.remover(cargo.getIdCargo(), Cargo.class);
	}
	
	@Override
	public void salvar(Cargo entidade) {

		if(entidade.getEstabelecimento().getIdEstabelecimento() == null)
		{
			super.salvar(entidade);
		}
		else
		{
			super.atualizar(entidade);
		}
	}
	
	public Cargo buscarCargoPelaDescricao(String descricao, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", descricao);
		parametros.put("id_estabelecimento", id_estabelecimento);
		
		return super.buscarUmResultado(Cargo.BUSCA_CARGO_POR_DESCRICAO, parametros);
	}
	
	public List<Cargo> buscarCargosPeloEstabelecimento(Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("id_estabelecimento", id_estabelecimento);
		
		return super.buscarResultados(Cargo.BUSCA_CARGOS_POR_ESTABELECIMENTO, parametros);
	}
}
