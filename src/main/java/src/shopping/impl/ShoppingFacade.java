package src.shopping.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.shopping.inter.IAvailabilityManager;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IShoppingFacade;
import src.shopping.inter.IPurchaseManager;

@RequestScoped
public class ShoppingFacade implements IShoppingFacade {
	
	@Inject
	private IAvailabilityManager availabilityManager;
	@Inject
	private ICartManager cartManager;
	@Inject
	private IPurchaseManager purchaseManager;

	
	@Override
	public List<Product> getAvail() {
		return availabilityManager.getAvail();
	}

	@Override
	public List<Articulo> getAvail(Product prod) {
		return availabilityManager.getAvail(prod);
	}

	@Override
	public List<Oferta> getAvail(Articulo articulo) {
		return availabilityManager.getAvail(articulo);
	}

	@Override
	public Cart addItemToCart(Oferta item) {
		return cartManager.addItem(item);
	}

	@Override
	public Cart removeItemFromCart(Oferta item) {
		return cartManager.removeItem(item);
	}

	@Override
	public Cart resetCart() {
		return cartManager.reset();
	}

	@Override
	public Order purchaseConfirm() {
		return purchaseManager.confirm();
	}

	@Override
	public Order createOrder() {
		return purchaseManager.createOrder();
	}

	public IAvailabilityManager getAvailabilityManager() {
		return availabilityManager;
	}

	public void setAvailabilityManager(IAvailabilityManager availabilityManager) {
		this.availabilityManager = availabilityManager;
	}

	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}

	public IPurchaseManager getPurchaseManager() {
		return purchaseManager;
	}

	public void setPurchaseManager(IPurchaseManager purchaseManager) {
		this.purchaseManager = purchaseManager;
	}


	
	

}