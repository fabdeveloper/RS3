package src.shopping.impl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.factory.IBeanFactory;
import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IValuationManager;

@SessionScoped
public class CartManager implements ICartManager, Serializable {
	
	private static final long serialVersionUID = 1L;


	@Inject
	private IValuationManager valuationManager; // TODO : obtener esto con serviceLocator
	@Inject
	private IServiceLocator serviceLocator;	
	
	private Cart cart;
	private IBeanFactory<Cart> factory; // TODO : obtener esto con serviceLocator
	
	
	
	public IValuationManager getValuationManager() {
		return valuationManager;
	}

	public void setValuationManager(IValuationManager valuationManager) {
		this.valuationManager = valuationManager;
	}

	public IBeanFactory<Cart> getFactory() {
		if(factory == null){
			factory = serviceLocator.getCartServices().getGestorE().getFactory();
		}
		return factory;
	}

	public void setFactory(IBeanFactory<Cart> factory) {
		this.factory = factory;
	}


	@Override
	public Cart addItem(Oferta item, Integer numItems) {
		if(cart == null)reset();
		
		// crear CartItem
		CartItem nuevoItem = serviceLocator.getCartItemServices().getGestorE().getFactory().crear();
		nuevoItem.setCounter(numItems);
		nuevoItem.setOferta(item);
		nuevoItem.setCart(cart);
		
		// agregar item
		cart.getListaItems().add(nuevoItem);
		cart.setValue(valuate()); // TODO - obtener ValuationManager a traves de serviceLocator
		
		return cart;
	}

	@Override
	public Cart removeItem(CartItem item) {
		cart.getListaItems().remove(item);
		cart.setValue(valuate());

		return cart;
	}

	@Override
	public Cart reset() {
		cart = getFactory().crear();
		if(cart.getListaItems() == null)cart.setListaItems(new ArrayList<CartItem>());
		cart.setValue(valuate());

		return cart;
	}
	
	private Float valuate(){
		Float result = 0f;
		valuationManager.setListaItems(cart.getListaItems());
		result = valuationManager.valuate();
		return result;
	}

	@Override
	public Cart getCart() {
		return cart;
	}
	
	@Override
	public Boolean isCartEmpty(){		
		return getCart().getListaItems().size()>0;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	

}
