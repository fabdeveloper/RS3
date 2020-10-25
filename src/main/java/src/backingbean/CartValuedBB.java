package src.backingbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import src.entity.Cart;


@Named
@SessionScoped
public class CartValuedBB implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Cart cart;
	
	public void moreItems(){
		
	}
	
	public void removeItem(){
		
	}
	
	public void reset(){
		
	}
	
	public void purchaseConfirm(){
		
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
