package controlador;

import java.util.List;

import util.interfaces.IController;
import basicas.Ranking;
import dados.RankingDAO;
import dados.geral.DAOFactory;

public class RankingController 
implements IController<Ranking>{
	
	private RankingDAO rankingDAO;
	
	public RankingController()
	{
		this.setRankingDAO(DAOFactory.getRankingDAO());
	}
	
	@Override
	public void cadastrar(Ranking entidade) {
		this.rankingDAO.inserir(entidade);
	}

	@Override
	public void remover(Ranking entidade) {
		this.rankingDAO.remover(entidade);
	}

	@Override
	public void remover(long id) {
		this.rankingDAO.remover(buscar(id));
	}

	@Override
	public void alterar(Ranking entidade) {
		this.rankingDAO.alterar(entidade);
	}

	@Override
	public Ranking buscar(long id) {
		return this.rankingDAO.buscarPorChave(id);
	}

	@Override
	public List<Ranking> buscar() {
		return this.rankingDAO.listarTodos();
	}

	public RankingDAO getRankingDAO() {
		return rankingDAO;
	}

	public void setRankingDAO(RankingDAO rankingDAO) {
		this.rankingDAO = rankingDAO;
	}
	
}
