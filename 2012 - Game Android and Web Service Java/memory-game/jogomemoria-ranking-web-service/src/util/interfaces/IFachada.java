package util.interfaces;

import java.util.List;

import basicas.Ranking;

public interface IFachada {
	
	public void incluirRanking(Ranking ranking);
	public void removerRanking(Ranking ranking);
	public void alterarRanking(Ranking ranking);
	public Ranking buscarRanking(long id);
	public List<Ranking> buscarRanking();	
}
