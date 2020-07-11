package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Product;

//@Stateless
@RequestScoped
public class ProductDao extends AbstractDao<Product>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public ProductDao() {
		super(Product.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
