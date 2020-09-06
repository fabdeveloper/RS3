package src.service;

import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class ServiceLocator {
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;
	
	public EntityManager getEntityManager(){
		return em;
	}

}
