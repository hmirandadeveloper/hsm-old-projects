package acao;

import util.Fachada;
import basicas.Ranking;

public class RankingWS {
	
	public boolean regitrarRanking(String nome, String pontuacao, String data)
	{
		if((nome != null && !nome.equals("")) 
				&& (pontuacao != null && !pontuacao.equals("")) 
				 &&(data != null && !data.equals("")))
		{
			Ranking ranking = new Ranking();
			ranking.setNome(nome);
			ranking.setPontuacao(pontuacao);
			ranking.setData(data);
			
			Fachada.getInstancia().incluirRanking(ranking);
			
			return true;
		}
		else
		{
			return false;
		}
	}
}
