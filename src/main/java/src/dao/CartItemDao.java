package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.CartItem;

@RequestScoped
public class CartItemDao extends AbstractDao<CartItem> {

	public CartItemDao() {
		super(CartItem.class);
	}

}
