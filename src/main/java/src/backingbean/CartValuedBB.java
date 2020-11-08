package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Cart;
import src.entity.Oferta;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class CartValuedBB{

	@Inject 
	private IShoppingFacade shoppingFacade;
	
	private Cart cart;
	private Oferta itemSeleccionado;
	
	
	
	public void moreItems(){
		
	}
	
	public void removeItem(){
		shoppingFacade.removeItemFromCart(itemSeleccionado);
	}
	
	public void reset(){
		shoppingFacade.resetCart();
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

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	public Oferta getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Oferta itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}
	
	

	
	

}
