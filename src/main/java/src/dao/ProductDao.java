package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Product;
import src.service.ServiceLocator;

//@Stateless
@RequestScoped
public class ProductDao extends AbstractDao<Product>{
	
	@Inject
	private ServiceLocator sl;
	
	private EntityManager em;

	public ProductDao() {
		super(Product.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
			em = sl.getEntityManager();
		}
		return em;
	}

}
