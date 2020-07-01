package src.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDao<T> {
	
	private Class<T> entityClass;
	
	public AbstractDao(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public void create(T entity){
		getEntityManager().persist(entity);		
	}
	
	public void edit(T entity){
		getEntityManager().merge(entity);
	}
	
	public void remove(T entity){
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	public T find(Object id){
		return getEntityManager().find(entityClass, id);		
	}
	
	public int count(){
		
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();        

	}
	
	public List<T> getAll(){
		
		EntityManager em = getEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> pet = cq.from(entityClass);
		cq.select(pet);
		TypedQuery<T> q = em.createQuery(cq);
		return q.getResultList();
		
	}
		

}