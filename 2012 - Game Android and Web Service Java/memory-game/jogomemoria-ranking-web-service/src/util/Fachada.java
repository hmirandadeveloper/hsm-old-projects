package util;

import java.util.List;

import util.interfaces.IFachada;
import basicas.Ranking;
import controlador.RankingController;

public class Fachada implements IFachada {
	
	private RankingController rankingController;
	
	private static Fachada fachada;
	
	private Fachada()
	{
		this.setRankingController(new RankingController());
	}
	
	public static Fachada getInstancia()
	{
		if(fachada == null)
		{
			fachada = new Fachada();
		}
		
		return fachada;
	}

	@Override
	public void incluirRanking(Ranking ranking) {
		this.rankingController.cadastrar(ranking);
	}

	@Override
	public void removerRanking(Ranking ranking) {
		this.rankingController.remover(ranking);
	}

	@Override
	public void alterarRanking(Ranking ranking) {
		this.rankingController.alterar(ranking);
	}

	@Override
	public Ranking buscarRanking(long id) {
		return this.rankingController.buscar(id);
	}

	@Override
	public List<Ranking> buscarRanking() {
		return rankingController.buscar();
	}

	public RankingController getRankingController() {
		return rankingController;
	}

	public void setRankingController(RankingController rankingController) {
		this.rankingController = rankingController;
	}
}
