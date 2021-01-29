package src.dao;

import java.util.List;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import src.inter.IServiceLocator;
import src.interceptors.AuditInterceptor;
import src.interceptors.Auditor;
import src.interceptors.DaoAuditory;
import src.service.ServiceLocator;

@Interceptors(AuditInterceptor.class)
public abstract class AbstractDao<T> implements IDao<T> {
	
	@Inject
	private IServiceLocator sl;	
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public AbstractDao(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	@Override
	public EntityManager getEntityManager() {
		if(em == null){
			em = sl.getEntityManager();
		}
		return em;
	}
	
	@Interceptors(AuditInterceptor.class)	
	@Override
	public void create(T entity){
		getEntityManager().persist(entity);		
	}
	@Auditor
	@Override
	public T edit(T entity){
		T nuevo = getEntityManager().merge(entity);
		return nuevo;
	}
	@Auditor
	@Override
	public void remove(T entity){
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	@Auditor
	@Override
	public T find(Object id){
		return getEntityManager().find(entityClass, id);		
	}
	
	@Auditor
	@Override
	public int count(){
		
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();        

	}
	
	@Auditor
	@Override
	public List<T> getAll(){
		
		EntityManager em = getEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> pet = cq.from(entityClass);
		cq.select(pet);
		TypedQuery<T> q = em.createQuery(cq);
		return q.getResultList();
		
	}
	
	@Auditor
	@Override
	public T createNamedQuery(String queryname, String paramname, String paramvalue){
		return getEntityManager().createNamedQuery(queryname, entityClass).setParameter(paramname, paramvalue).getSingleResult();
	}
	
	@Auditor
	@Override
	public List<T> createNamedQueryListResult(String queryname, String paramname, String paramvalue){
		return getEntityManager().createNamedQuery(queryname, entityClass).setParameter(paramname, paramvalue).getResultList();
	}
	
	@Auditor
	@Override
	public List<T> createNamedQueryListResultIntParam(String queryname, String paramname, Integer paramvalue){
		return getEntityManager().createNamedQuery(queryname, entityClass).setParameter(paramname, paramvalue).getResultList();
	}
		

}
