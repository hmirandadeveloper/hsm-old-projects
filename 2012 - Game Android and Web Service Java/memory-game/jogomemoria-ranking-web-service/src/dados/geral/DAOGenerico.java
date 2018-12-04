package dados.geral;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import basicas.Ranking;

/**
 * PSC
 * 
 * @param <Entidade, PK>
 */
public abstract class DAOGenerico<Entidade> {

	private EntityManager entityManager;
	private Class<Entidade> classePersistente;

	@SuppressWarnings("unchecked")
	public DAOGenerico(EntityManager em){
		this.setEntityManager(em);
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
	    classePersistente = (Class<Entidade>) parameterizedType.getActualTypeArguments()[0];  
	}
	
	/**
	 * Executa o merge do objeto que se encontra em mem�ria.
	 * 
	 * @param objeto
	 *            a ser realizado o merge
	 * @return objeto que foi executado o merge
	 */
	public Entidade alterar(Entidade objeto) {

		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		
		objeto = getEntityManager().merge(objeto);
		
		tx.commit();
		
		return objeto;
	}

	/**
	 * Salva o objeto atual na base de dados.
	 * 
	 * @param objeto a ser salvo
	 */
	public void inserir(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();		
		try {
			tx.begin();
			getEntityManager().persist(objeto);
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " salvo com sucesso");
		} catch (PersistenceException e) {
			tx.rollback();
		}
	}

	/**
	 * Salva o objeto atual na base de dados.
	 * 
	 * @param objeto
	 *            a ser salvo
	 */
	public final void inserirColecao(Collection<Entidade> colecao) {
		try {
			EntityTransaction tx = getEntityManager().getTransaction();
			tx.begin();

			for (Entidade entidade : colecao) {
				getEntityManager().persist(entidade);	
			}
			
			tx.commit();
			
			System.out.println(classePersistente.getSimpleName() + " salvos com sucesso: " + colecao.size());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove o objeto da base de dados.
	 * 
	 * @param objeto
	 *            a ser removido
	 */
	public final void remover(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		
		System.out.println("ID: " + ((Ranking)objeto).getId());
		
		tx.begin();

		// Este merge foi incluido para permitir a exclusao de objetos no estado Detached
		objeto = getEntityManager().merge(objeto);
		
		getEntityManager().remove(objeto);
		
		tx.commit();
		
		System.out.println(classePersistente.getSimpleName() + " removido com sucesso");		
	}

	
	
	/**
	 * Busca o objeto uma vez passado sua chave como par�metro.
	 * 
	 * @param chave
	 *            identificador
	 * @return Objeto do tipo T
	 */
	public final Entidade buscarPorChave(Serializable chave) {
		Entidade instance = null;
		try {
			instance = (Entidade) getEntityManager().find(getClassePersistente(), chave);
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return instance;
	}

	/**
	 * Atualiza o objeto que se encontra em mem�ria.
	 * 
	 * @param object
	 *            objeto a ser atualizado
	 */
	public final void refresh(Entidade object) {
		getEntityManager().refresh(object);
	}
	
	/**
	 * Busca o objeto de acordo com o objeto preenchido com os valores passado
	 * como exemplo.
	 * 
	 * @param objeto
	 *            utilizado para realizar a busca
	 * @param ordenacoes
	 *            lista de crit�rios de ordena��o
	 * @return Lista de objetos retornada
	 */
	@SuppressWarnings("unchecked")
	public final List<Entidade> pesquisar(Entidade objeto) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = criaExemplo(objeto);
		Criteria criteria = session.createCriteria(objeto.getClass()).add(
				example);
		return (List<Entidade>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public final List<Entidade> pesquisar(Entidade objeto, Order... ordenacoes) {
		Example example = criaExemplo(objeto);
		Criteria criteria = criaCriteria().add(example);
		for(Order ord : ordenacoes){
			criteria.addOrder(ord);
		}
		return (List<Entidade>) criteria.list();
	}

	/**
	 * Busca o objeto de acordo com o objeto preenchido com os valores passado
	 * como exemplo.
	 * 
	 * @param objeto
	 * @param indiceInicial
	 * @param indiceFinal
	 * @param ordenacoes
	 *            lista de crit�rios de ordena��o.
	 * @return Lista de orden
	 */
	@SuppressWarnings("unchecked")
	public final List<Entidade> pesquisar(Entidade objeto, Integer indiceInicial,
			Integer quantidade) {
		Example example = criaExemplo(objeto);
		Criteria criteria = criaCriteria().add(example);
		criteria.setFirstResult(indiceInicial);
		criteria.setMaxResults(quantidade);

		return (List<Entidade>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public final List<Entidade> pesquisar(Entidade objeto, Integer indiceInicial,
			Integer quantidade, Order... ordenacoes) {
		Example example = criaExemplo(objeto);
		Criteria criteria = criaCriteria().add(example);
		criteria.setFirstResult(indiceInicial);
		criteria.setMaxResults(quantidade);
		for(Order ord : ordenacoes){
			criteria.addOrder(ord);
		}
		return (List<Entidade>) criteria.list();
	}
	/**
	 * Retorna a quantidade total de objetos para aquela entidade espec�fica.
	 * 
	 * @return quantidade total de objetos
	 */
	public final int buscaQuantidadeTotal() {
		Criteria criteria = criaCriteria();
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
	}

	/**
	 * Busca todos os objetos para aquela entidade espec�fica.
	 * 
	 * @param ordenacoes
	 *            lista de ordena��es para pesquisa
	 * @return lista de todos os objetos da entidade
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listarTodos() {
		List<Entidade> results = null;
		try {
			Query query = getEntityManager().createQuery(
					"from " + getClassePersistente().getName());
			results = query.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return results;
	}

	/**
	 * 
	 * Busca todos os objetos de uma entidade espec�fica de um �ndice inicial
	 * at� um �ndice final.
	 * 
	 * @param indiceInicial
	 *            indice inicial da busca
	 * @param indiceFinal
	 *            indice final da pesquisa.
	 * @param ordenacoes
	 *            lista de ordena��o a ser criado
	 * @return uma lista de objetos do tipo T
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listarTodos(Integer indiceInicial, Integer quantidade) {
		List<Entidade> results = null;
		try {
			Query query = getEntityManager().createQuery(
					"from " + getClassePersistente().getName());
			query.setFirstResult(indiceInicial);
			query.setMaxResults(quantidade);

			results = (List<Entidade>) query.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return results;
	}

	/**
	 * 
	 * Busca todos os objetos de uma entidade espec�fica de um �ndice inicial
	 * at� um �ndice final.
	 * 
	 * @param indiceInicial
	 *            indice inicial da busca
	 * @param indiceFinal
	 *            indice final da pesquisa.
	 * @param ordenacoes
	 *            lista de ordena��o a ser criado
	 * @return uma lista de objetos do tipo T
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listarTodos(Integer indiceInicial, Integer quantidade, Order... ordenacoes) {
		List<Entidade> results = null;
		try {
			Query query = getEntityManager().createQuery(
					"from " + getClassePersistente().getName() 
					+ adicionaOrderByHql(ordenacoes));
			query.setFirstResult(indiceInicial);
			query.setMaxResults(quantidade);
			results = (List<Entidade>) query.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return results;
	}

	/**
	 * Adiciona o orderBy no final da query a ser utilizada.
	 * 
	 * @param ordenacoes
	 *            a serem utilizadas para a busca
	 * @return string com o orderBy
	 */
	protected final static String adicionaOrderByHql(Order... ordenacoes) {
		String result = "";
		if (ordenacoes.length > 0) {
			StringBuilder builder = new StringBuilder(" order by ");
			for (int i = 0; i < ordenacoes.length - 1; i++) {
				builder.append(ordenacoes[i].toString());
				builder.append(", ");
			}
			builder.append(ordenacoes[ordenacoes.length - 1]);
			result = builder.toString();
		}

		return result;
	}

	/**
	 * M�todo utilizado para criar o objeto Example. Este objeto � utilizado
	 * para realizar a busca por exemplo.
	 * 
	 * @param objeto
	 *            sobre o qual o Example ser� criado
	 * @return em objeto do tipo Example
	 */
	protected final Example criaExemplo(Entidade objeto) {

		Example example = Example.create(objeto);
		example.enableLike(MatchMode.ANYWHERE);
		example.excludeZeroes();
		example.ignoreCase();

		return example;
	}

	/**
	 * Retorna o objeto da clases Criteria.
	 * 
	 * @return um objeto do tipo Criteria do Hibernate
	 */
	protected final Criteria criaCriteria() {
		Session session = (Session) getEntityManager().getDelegate();
		return session.createCriteria(getClassePersistente());
	}
	
	/**
	 * Utilizado para se injetar o Entity manager no DAO.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	/**
	 * Busca a classe persistente do objeto utilizado na classe.
	 * 
	 * @return classe persistente
	 */
	protected final Class<Entidade> getClassePersistente() {
		return classePersistente;
	}
	
}
