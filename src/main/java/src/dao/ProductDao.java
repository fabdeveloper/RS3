package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Product;
import src.service.ServiceLocator;

@RequestScoped
public class ProductDao extends AbstractDao<Product>{

	public ProductDao() {
		super(Product.class);
	}


}
