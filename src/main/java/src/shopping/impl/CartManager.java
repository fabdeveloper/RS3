package src.shopping.impl;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.exception.StockException;
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
		
		// si oferta existe entonces incrementar numItems
		Integer oferta_id = item.getId();
		CartItem cartItem = getItemByOfertaId(oferta_id);

		if(cartItem == null){ // oferta no existe en el carrito
			// crear CartItem
			cartItem = serviceLocator.getCartItemServices().getTransferObject();
			cartItem.setCounter(numItems);
			cartItem.setOferta(item);
			cartItem.setCart(cart);
			// agregar item
			cart.getListaItems().add(cartItem);
			
		}else{ // existe
			Integer newCounter = cartItem.getCounter() + numItems;
			if(newCounter > item.getStock()){
				String mensaje = "Stock insuficiente - Oferta = " + item.getName() + ", maximo = " + item.getStock() + ", solicitado = " + newCounter;
				throw new StockException(mensaje);
			}else{
				cartItem.setCounter(newCounter);
			}
		}
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
		if(cart == null)reset();
		return cart;
	}
	
	@Override
	public Boolean isCartEmpty(){		
		return !(getCart().getListaItems().size()>0);
	}

	@Override
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

	@Override
	public Cart modifyItem(Integer id, Integer numItems) {
		CartItem item = getItemById(id);
		item.setCounter(numItems);	
		cart.setValue(valuate());
		return cart;
	}

	@Override
	public CartItem getItemById(Integer id) {
		CartItem item = null;
		for(CartItem ci : cart.getListaItems()){
			if(ci.getId().compareTo(id) == 0){
				item = ci;
			}
		}
		return item;
	}
	
	@Override
	public CartItem getItemByOfertaId(Integer oferta_id){
		CartItem cartitem = null;		
		for(CartItem item : getCart().getListaItems()){
			if(oferta_id.compareTo(item.getOferta().getId()) == 0){
				cartitem = item;
			}
		}		
		return cartitem;		
	}

	@Override
	public Integer getNumItems() {
		return getCart().getListaItems().size();
	}
	

}
