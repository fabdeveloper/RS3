package src.backingbean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Cart;
import src.shopping.impl.ShoppingFacade;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class CartValuedBB{

	@Inject 
	private IShoppingFacade shoppingFacade;
	
	private Cart cart;
	
	
	
	public void moreItems(){
		
	}
	
	public void removeItem(){
		
	}
	
	public void reset(){
		
	}
	
	public void createOrder(){
		shoppingFacade.createOrder();
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	

}
