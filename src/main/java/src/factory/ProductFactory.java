package src.factory;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

import src.entity.Product;

//@Stateless
@RequestScoped
public class ProductFactory implements BeanFactory<Product> {
	
	private Product product = new Product();

	@Override
	public Product crear() {
		return product.clone();
	}

}
