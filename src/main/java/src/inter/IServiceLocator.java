package src.inter;

import javax.persistence.EntityManager;

import src.entity.Articulo;
import src.entity.Grupo;
import src.entity.Product;
import src.entity.User;
import src.entityservices.IEntityServices;

public interface IServiceLocator {
	
	public EntityManager getEntityManager();
	
	public IEntityServices<Grupo> getGrupoServices();
	
	public IEntityServices<User> getUserServices();

}
