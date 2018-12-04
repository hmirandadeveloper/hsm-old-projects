package dados.geral;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dados.RankingDAO;

public abstract class DAOFactory {

	private static EntityManager manager;
	private static final EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("jogomemoria.db");
		if (manager == null || !manager.isOpen()) {
			manager = factory.createEntityManager();
		}
	}
	
	public static RankingDAO getRankingDAO(){
		RankingDAO dao = new RankingDAO(manager);
		return dao;
	}
}
