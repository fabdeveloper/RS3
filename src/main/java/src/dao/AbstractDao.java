package src.dao;

import java.util.List;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import src.exception.DBException;
import src.inter.IServiceLocator;
import src.interceptors.AuditInterceptor;
import src.interceptors.Auditor;
import src.interceptors.DaoAuditory;
import src.service.ServiceLocator;

@Interceptors(AuditInterceptor.class)
public abstract class AbstractDao<T> implements IDao<T> {
	
	@Inject
	private IServiceLocator sl;	
	
	private Class<T> entityClass;
	
	public AbstractDao(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	@Override
	public EntityManager getEntityManager() {
		EntityManager em = null;
		try{
			em = sl.getEntityManager();
		}catch(Throwable t){
			throw new DBException("EntityManager error", t);
		}
		return em;
	}
	
	@Interceptors(AuditInterceptor.class)	
	@Override
	public void create(T entity){
		try{
			getEntityManager().persist(entity);				
		}catch(Throwable t){
			throw new DBException("Entity NOT created : " + entity, t);
		}	
	}
	
	@Auditor
	@Override
	public T edit(T entity){
		T nuevo = null;
		try{
			nuevo = getEntityManager().merge(entity);				
		}catch(Throwable t){
			throw new DBException("Edit error : " + entity, t);
		}	
		return nuevo;
	}
	
	@Auditor
	@Override
	public void remove(T entity){
		try{
			getEntityManager().remove(getEntityManager().merge(entity));	
		}catch(Throwable t){
			throw new DBException("Error removing : " + entity, t);
		}		
	}
	
	@Auditor
	@Override
	public T find(Object id){
		T entity = null;
		try{
			entity = getEntityManager().find(entityClass, id);
		}catch(Throwable t){
			throw new DBException("Error finding entity : " + id, t);
		}
		return entity;		
	}
	
	@Auditor
	@Override
	public int count(){
		int response = -1;
		try{
			javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
	        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
	        javax.persistence.Query q = getEntityManager().createQuery(cq);
	        response =  ((Long) q.getSingleResult()).intValue();   			
		}catch(Throwable t){
			throw new DBException("Error counting ", t);
		}
		return response;        
	}
	
	@Auditor
	@Override
	public List<T> getAll(){
		List<T> result = null;
		
		EntityManager em = getEntityManager();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(entityClass);
			Root<T> pet = cq.from(entityClass);
			cq.select(pet);
			TypedQuery<T> q = em.createQuery(cq);
			result = q.getResultList();
		}catch(Throwable t){
			throw new DBException("AbstractDao getAll() error ", t);
		}
		return result;		
	}
	
	@Auditor
	@Override
	public T createNamedQuery(String queryname, String paramname, String paramvalue){
		T entity = null;
		try{
			entity = getEntityManager().createNamedQuery(queryname, entityClass).setParameter(paramname, paramvalue).getSingleResult();
		}catch(Throwable t){
			throw new DBException("AbstractDao createNamedQuery() error - queryname =  " + queryname + ", paramname = " + paramname + ", paramvalue = " + paramvalue, t);
		}
		return entity;
	}
	
	@Auditor
	@Override
	public List<T> createNamedQueryListResult(String queryname, String paramname, String paramvalue){
		List<T> result = null;
		try{
			result = getEntityManager().createNamedQuery(queryname, entityClass).setParameter(paramname, paramvalue).getResultList();
		}catch(Throwable t){
			throw new DBException("AbstractDao createNamedQueryListResult() error - queryname = " + queryname + ", paramname = " + paramname + ", paramvalue = " + paramvalue, t);
		}
		return result;
	}
	
	@Auditor
	@Override
	public List<T> createNamedQueryListResultIntParam(String queryname, String paramname, Integer paramvalue){
		List<T> result = null;
		try{
			result = getEntityManager().createNamedQuery(queryname, entityClass).setParameter(paramname, paramvalue).getResultList();
		}catch(Throwable t){
			throw new DBException("AbstractDao createNamedQueryListResultIntParam() error - queryname = " + queryname + ", paramname = " + paramname + ", paramvalue = " + paramvalue, t);
		}
		return result;
	}
	
	@Override
	public List<T> createNamedQueryLimited(String queryname, Integer limit){
		List<T> result = null;
		try{
			result = getEntityManager().createNamedQuery(queryname, entityClass).setMaxResults(limit).getResultList();
		}catch(Throwable t){
			throw new DBException("AbstractDao createNamedQueryLimited() error - queryname = " + queryname + ", limit = " + limit);
		}
		return result;
	}
	
	
		

}
