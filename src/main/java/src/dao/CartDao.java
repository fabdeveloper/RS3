package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.Cart;

@RequestScoped
public class CartDao extends AbstractDao<Cart> {
	
	public CartDao(){
		super(Cart.class);
	}

}
