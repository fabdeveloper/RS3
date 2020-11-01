package src.shopping.impl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.Oferta;
import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IValuationManager;

@SessionScoped
public class CartManager implements ICartManager, Serializable {
	

	@Inject
	private IValuationManager valuationManager;
	
	private static final long serialVersionUID = 1L;
	
	private Cart cart;
	@Inject
	private IServiceLocator serviceLocator;

	@Override
	public Cart addItem(Oferta item) {
		if(cart == null)reset();
		cart.getListaOfertas().add(item);
		cart.setValue(valuate());
		
		return cart;
	}

	@Override
	public Cart removeItem(Oferta item) {
		cart.getListaOfertas().remove(item);
		cart.setValue(valuate());

		return cart;
	}

	@Override
	public Cart reset() {
		cart = serviceLocator.getCartServices().getGestorE().getFactory().crear();
		if(cart.getListaOfertas() == null)cart.setListaOfertas(new ArrayList<Oferta>());
		cart.setValue(valuate());

		return cart;
	}
	
	private Float valuate(){
		Float result = 0f;
		valuationManager.setItems(cart.getListaOfertas());
//		valuationManager.setUser(user);
		result = valuationManager.valuate();
		return result;
	}

	public Cart getCart() {
		return cart;
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
