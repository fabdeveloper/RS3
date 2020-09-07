package src.inter;

import javax.persistence.EntityManager;

public interface IServiceLocator {
	
	public EntityManager getEntityManager();

}
