package src.shopping.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.inter.IServiceLocator;
import src.shopping.inter.IAvailabilityManager;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IShoppingFacade;
import src.shopping.inter.IPurchaseManager;

@SessionScoped
public class ShoppingFacade implements IShoppingFacade, Serializable{
	

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ShoppingFacade.class.getName());
	
	@Inject private IServiceLocator serviceLocator;
	@Inject
	private IAvailabilityManager availabilityManager;
	@Inject
	private ICartManager cartManager;
	@Inject
	private IPurchaseManager purchaseManager;
	
	private Oferta ofertaSeleccionada;

	@Override
	public Oferta getOfertaSeleccionada() {
		return ofertaSeleccionada;
	}

	public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
		this.ofertaSeleccionada = ofertaSeleccionada;
	}

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
	public String addItemToCart(Oferta item) {
		cartManager.addItem(item);
		return serviceLocator.getViewStateMachine().setCartView();
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
	public String purchaseConfirm() {
		return purchaseManager.confirm();
	}

	@Override
	public String createOrder() {
		return purchaseManager.createOrder();
	}

	public IAvailabilityManager getAvailabilityManager() {
		return availabilityManager;
	}

	public void setAvailabilityManager(IAvailabilityManager availabilityManager) {
		this.availabilityManager = availabilityManager;
	}

	@Override
	public IPurchaseManager getPurchaseManager() {
		return purchaseManager;
	}

	public void setPurchaseManager(IPurchaseManager purchaseManager) {
		this.purchaseManager = purchaseManager;
	}

	@Override
	public Cart getCart() {
		return cartManager.getCart();
	}

	@Override
	public Order getOrder() {		
		return purchaseManager.getOrder();
	}

	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}

//	@Override
//	public void setOrder(Order neworder) {
//		purchaseManager.setOrder(neworder);
//		
//	}

	@Override
	public String findOrder(Integer order_id) {
		logger.log(Level.INFO, "ShoppingFacade-findOrder - order_id = " + order_id);
		
		Order order = purchaseManager.findOrder(order_id);
		
		logger.log(Level.INFO, "ShoppingFacade-findOrder - encontrada order = " + order);

		return serviceLocator.getViewStateMachine().setOrderView();
	}

	@Override
	public String cancelOrder() {
		purchaseManager.cancelOrder();
		return serviceLocator.getViewStateMachine().setOrderView();

	}

	@Override
	public String deleteOrder() {
		purchaseManager.deleteOrder();
		return serviceLocator.getViewStateMachine().setOrderView();
	}

	@Override
	public String setPaymentProcessOK(Boolean result) {
		purchaseManager.setPaymentProcessOK(result);
		return purchaseManager.confirm();
	}

	@Override
	public String showOfertaDetail(Oferta oferta) {
		setOfertaSeleccionada(oferta);		
		return serviceLocator.getViewStateMachine().setOfertaView();
	}

	@Override
	public String showOrder() {
		return serviceLocator.getViewStateMachine().setOrderView();
	}

	@Override
	public String nuevaCompra() {
		purchaseManager.reset();
		cartManager.reset();

		return serviceLocator.getViewStateMachine().setAvailabilityView();
	}

	@Override
	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	@Override
	public String invalidateSession() {		
		serviceLocator.getSessionManager().invalidateSession();
		return serviceLocator.getViewStateMachine().setHomeView();		
	}
	
	@Override
	public Boolean isClient(){			
		return serviceLocator.getSessionManager().isClient();
	}
	
	@Override
	public String getCallerName(){		
		return serviceLocator.getSessionManager().getCallerName();		
	}




	
	

}
