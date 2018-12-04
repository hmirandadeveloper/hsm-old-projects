package persistencia.generico;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

public abstract class GenericDAO<T> {
	private final static String UNIT_NAME = "fastLunchPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;
	
	@Resource
	private UserTransaction tx;
	
	@Resource
	private SessionContext sessionContext;
	
	private Class<T> classeEntidade;
	
	public GenericDAO(Class<T> classeEntidade)
	{
		this.classeEntidade = classeEntidade;
	}
	
	public void salvar(T entidade)
	{
		System.out.println("Salvando no banco!");

			this.em.persist(entidade);
	}
	
	protected void remover(Object id, Class<T> classe)
	{
		T entidadeASerRemovida = this.em.getReference(classe, id);
		
		this.em.remove(entidadeASerRemovida);
	}
	
	public T atualizar(T entidade)
	{
		return this.em.merge(entidade);
	}
	
	public T buscar(Object idEntidade)
	{
		return this.em.find(classeEntidade, idEntidade);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<T> buscarTodos()
	{
		CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(classeEntidade));
		return em.createQuery(cq).getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	protected T buscarUmResultado(String namedQuery, Map<String, Object> parametros)
	{
		System.out.println("Busca por um resultado!");
		
		T resultado = null;
		
		try
		{
			Query query = em.createNamedQuery(namedQuery);
			System.out.println("Query criada!");
			System.out.println("Query: " + namedQuery);
			if(parametros != null && !parametros.isEmpty())
			{
				popularParamentrosDaQuery(query, parametros);
			}
			
			resultado = (T) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			System.out.println("Nenhum resultado encontrado!");
			resultado = null;
			
		}catch(Exception e)
		{
			System.out.println("Erro na execução da Query: " + e.getMessage());
		}
		
		return resultado;
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected List<T> buscarResultados(String namedQuery, Map<String, Object> parametros)
	{
		System.out.println("Busca vários resultados!");
		System.out.println("Query: " + namedQuery);
		
		List<T> resultados = null;
		
		try
		{
			Query query = em.createNamedQuery(namedQuery);
			System.out.println("Query criada!");
			if(parametros != null && !parametros.isEmpty())
			{
				popularParamentrosDaQuery(query, parametros);
			}
			
			resultados = (List<T>) query.getResultList();
			System.out.println("Qtd.: " + resultados.size());
			System.out.println("Consulta Ok!");
		}catch(NoResultException e)
		{
			System.out.println("Nenhum resultado encontrado!");
			
		}catch(Exception e)
		{
			System.out.println("Erro na execução da Query: " + e.getMessage());
		}
		
		return resultados;
	}
	
	private void popularParamentrosDaQuery(Query query, 
			Map<String, Object> parametros)
	{
		for(Entry<String, Object> entry : parametros.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public UserTransaction getTx() {
		return tx;
	}

	public SessionContext getSessionContext() {
		return sessionContext;
	}
}
