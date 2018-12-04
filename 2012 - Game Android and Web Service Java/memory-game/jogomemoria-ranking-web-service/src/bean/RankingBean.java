package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.Fachada;
import basicas.Ranking;
import bean.data.model.DMGeral;

@ManagedBean
@ViewScoped
public class RankingBean
implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Ranking> rankings;
	private DMGeral<Ranking> rankingsModelo;
	
	public RankingBean()
	{
		inicializar();
	}
	
	private void inicializar()
	{
		carregarTabela();
	}
	
	public void carregarTabela()
	{
		this.rankings = Fachada.getInstancia().buscarRanking();
		this.setRankingsModelo(new DMGeral<Ranking>(this.rankings));
	}

	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRanking(List<Ranking> rankings) {
		this.rankings = rankings;
	}

	public DMGeral<Ranking> getRankingsModelo() {
		return rankingsModelo;
	}

	public void setRankingsModelo(DMGeral<Ranking> rankingsModelo) {
		this.rankingsModelo = rankingsModelo;
	}
}
