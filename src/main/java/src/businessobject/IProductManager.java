package src.businessobject;

import java.util.List;

import src.entity.Product;

public interface IProductManager {
	
	public List<Product> getProductList();
	
	public Product getProductByName(String name);

}
