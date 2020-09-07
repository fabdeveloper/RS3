package src.service;

import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.inter.IServiceLocator;

@Singleton
public class ServiceLocator implements IServiceLocator{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;
	
	@Override
	public EntityManager getEntityManager(){
		return em;
	}

}
