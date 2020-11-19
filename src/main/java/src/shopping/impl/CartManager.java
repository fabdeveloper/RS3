package src.shopping.impl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.Oferta;
import src.factory.IBeanFactory;
import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IValuationManager;

@SessionScoped
public class CartManager implements ICartManager, Serializable {
	
	private static final long serialVersionUID = 1L;


	@Inject
	private IValuationManager valuationManager;
	@Inject
	private IServiceLocator serviceLocator;	
	
	private Cart cart;
	private IBeanFactory<Cart> factory;
	
	
	
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
		cart = factory.crear();
		if(cart.getListaOfertas() == null)cart.setListaOfertas(new ArrayList<Oferta>());
		cart.setValue(valuate());

		return cart;
	}
	
	private Float valuate(){
		Float result = 0f;
		valuationManager.setListaItems(cart.getListaOfertas());
		result = valuationManager.valuate();
		return result;
	}

	@Override
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
