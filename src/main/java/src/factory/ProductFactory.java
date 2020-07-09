package src.factory;

import src.entity.Product;

public class ProductFactory implements BeanFactory<Product> {
	
	private Product product = new Product();

	@Override
	public Product crear() {
		return product.clone();
	}

}
