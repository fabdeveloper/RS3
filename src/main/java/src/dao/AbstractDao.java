package src.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import src.inter.IServiceLocator;
import src.service.ServiceLocator;


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
	
	
	@Override
	public void create(T entity){
		getEntityManager().persist(entity);		
	}
	@Override
	public void edit(T entity){
		getEntityManager().merge(entity);
	}
	@Override
	public void remove(T entity){
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	@Override
	public T find(Object id){
		return getEntityManager().find(entityClass, id);		
	}
	
	@Override
	public int count(){
		
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();        

	}
	
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
	
	@Override
	public T namedQuery(String name, String param){
		return getEntityManager().createNamedQuery(name, entityClass).setParameter(0, param).getSingleResult();
	}
		

}
