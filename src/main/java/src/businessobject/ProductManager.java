package src.businessobject;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Product;
import src.inter.IServiceLocator;

@SessionScoped
public class ProductManager implements IProductManager, Serializable {


	@Inject
	private IServiceLocator serviceLocator;
	
	private List<Product> productList;
	private boolean validProductList;
	
	
	
	

	public boolean isValidProductList() {
		return validProductList;
	}

	public void setValidProductList(boolean validProductList) {
		this.validProductList = validProductList;
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	@Override
	public List<Product> getProductList() {
		if(productList == null || !isValidProductList()){
			productList = serviceLocator.getProductServices().readAll();
			setValidProductList(true);
		}		
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public Product getProductByName(String name) {
		Product sel = null;

		for(Product prod : getProductList()){
			if(prod.getName().matches(name)){
				sel = prod;
				break;
			}
		}		
		return sel;
	}

}
