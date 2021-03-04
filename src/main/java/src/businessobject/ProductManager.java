package src.businessobject;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Product;
import src.entitymanager.AbstractEntityManager;
import src.inter.IServiceLocator;

@SessionScoped
public class ProductManager extends AbstractEntityManager<Product> implements IProductManager, Serializable {


	@Override
	public Product getByName(String name) {
		Product sel = null;

		for(Product prod : getAll()){
			if(prod.getName().matches(name)){
				sel = prod;
				break;
			}
		}		
		return sel;
	}

	@Override
	public Product getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
