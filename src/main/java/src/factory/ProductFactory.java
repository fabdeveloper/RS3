package src.factory;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

import src.entity.Product;

@RequestScoped
public class ProductFactory extends AbstractBeanFactory<Product> {	

}
