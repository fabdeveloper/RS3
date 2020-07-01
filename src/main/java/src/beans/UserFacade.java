package src.beans;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.User;

@Stateless
public class UserFacade extends AbstractFacade<User>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public UserFacade() {
		super(User.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
