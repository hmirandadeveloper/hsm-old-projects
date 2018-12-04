package dados;

import javax.persistence.EntityManager;

import basicas.Ranking;
import dados.geral.DAOGenerico;

public class RankingDAO extends DAOGenerico<Ranking> {

	public RankingDAO(EntityManager em) {
		super(em);
	}

}
