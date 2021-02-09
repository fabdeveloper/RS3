package src.exception;

import src.entity.CartItem;
import src.entity.Oferta;

public class StockException extends RS3Exception {
	
	private CartItem cartItem;
	private Integer disponible;
	private Oferta oferta;


	public CartItem getCartItem() {
		return cartItem;
	}

	public StockException setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
		return this;
	}

	public Integer getDisponible() {
		return disponible;
	}

	public StockException setDisponible(Integer disponible) {
		this.disponible = disponible;
		return this;

	}

	public Oferta getOferta() {
		return oferta;
	}

	public StockException setOferta(Oferta oferta) {
		this.oferta = oferta;
		return this;

	}

	public StockException() {
		// TODO Auto-generated constructor stub
	}

	public StockException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public StockException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public StockException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public StockException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
