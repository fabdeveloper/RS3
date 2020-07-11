package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.User;

//@Stateless
@RequestScoped
public class UserDao extends AbstractDao<User>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public UserDao() {
		super(User.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
