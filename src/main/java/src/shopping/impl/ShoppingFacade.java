package src.shopping.impl;

/**************************************************************************/
/*	Session Facade
 * 	Dispatcher View
 * 	
 * 
 * 	Author : Fabricio Tosi
 */
/**************************************************************************/

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.inter.IServiceLocator;
import src.shopping.inter.IAvailabilityManager;
import src.shopping.inter.ICartManager;
import src.shopping.inter.ISessionManager;
import src.shopping.inter.IShoppingFacade;
import src.shopping.inter.IPurchaseManager;
import src.shopping.inter.IStockManager;
import src.shopping.inter.IViewStateMachine;

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
	@Inject 
	private IStockManager stockManager;
	@Inject
	private IViewStateMachine viewStateMachine;
	@Inject
	private ISessionManager sessionManager;
	
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
	public String addItemToCart(Oferta item, Integer numItems) {
//		try{
//			if(!isClient())sessionManager.callLogin();
//
//		}catch(Throwable t){
//			System.out.println("ShoppingFacade.addItemToCart - exception callLogin() - " + t.getMessage());
//			return "";
//		}
//		if(!isClient())return "";
		
		cartManager.addItem(item, numItems);
		purchaseManager.updateOrder();
		return viewStateMachine.setCartView();
	}

	@Override
	public String removeItemFromCart(CartItem item) {
		cartManager.removeItem(item);
		purchaseManager.updateOrder();
		return viewStateMachine.setCartView();
	}

	@Override
	public String resetCart() {
		cartManager.reset();
		purchaseManager.updateOrder();
		return viewStateMachine.setCartView();
	}
	
	@Override
	public String preConfirm() {
		String result = viewStateMachine.setOrderView();		
		try{
			purchaseManager.preConfirmation();
		}catch(Throwable t){
			result = viewStateMachine.setErrorView();
//			 setRollbackOnly
			serviceLocator.getSessionContext().setRollbackOnly();			
		}		
		return result;
	}

	@Override
	public String purchaseConfirm() {
		String result = viewStateMachine.setOrderView();
		try{
			purchaseManager.confirm();
		}catch(Throwable t){
			result = viewStateMachine.setErrorView();			
		}		
		return result;
	}

	@Override
	public String configOrder() {
		return viewStateMachine.setConfigView();
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

	@Override
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

		return viewStateMachine.setOrderView();
	}

	@Override
	public String cancelOrder() {
		purchaseManager.cancelOrder();
		return viewStateMachine.setOrderView();

	}

	@Override
	public String deleteOrder() {
		purchaseManager.deleteOrder();
		return viewStateMachine.setOrderView();
	}

	@Override
	public String setPaymentProcessOK(Boolean result) {
		purchaseManager.setPaymentProcessOK(result);
		return "";
	}

	@Override
	public String showOfertaDetail(Oferta oferta) {
		setOfertaSeleccionada(oferta);		
		return viewStateMachine.setOfertaView();
	}

	@Override
	public String showOrder() {
		return viewStateMachine.setOrderView();
	}

	@Override
	public String nuevaCompra() {
		purchaseManager.reset();
		cartManager.reset();

		return viewStateMachine.setAvailabilityView();
	}

	@Override
	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	@Override
	public String invalidateSession() {		
		sessionManager.invalidateSession();
		nuevaCompra();
		return viewStateMachine.setHomeView();		
	}
	
	@Override
	public Boolean isClient(){			
		return sessionManager.isClient();
	}
	
	@Override
	public String getCallerName(){		
		return sessionManager.getCallerName();		
	}

	@Override
	public String changeNumItems(Integer id, Integer numItems) {
		cartManager.modifyItem(id, numItems);
		purchaseManager.updateOrder();
		return viewStateMachine.setCartView();
	}

	@Override
	public IStockManager getStockManager() {
		return stockManager;
	}
	
	@Override
	public IViewStateMachine getViewStateMachine() {
		return viewStateMachine;
	}

	@Override
	public ISessionManager getSessionManager() {
		return sessionManager;
	}

	@Override
	public Boolean consumirStock(Integer oferta_id, Integer unidades) {		
		return stockManager.consumirStock(oferta_id, unidades);
	}

	@Override
	public Boolean recuperarStock(Integer oferta_id, Integer unidades) {
		return stockManager.recuperarStock(oferta_id, unidades);
	}

	@Override
	public String paymentError() {
		try{
			purchaseManager.paymentError();
		}catch(Throwable t){
			
		}		
		return getViewStateMachine().setErrorView();
	}

	@Override
	public Boolean loadPendingOrder() {
		System.out.println("ShoppingFacade - loadPendingOrder() - " + new Date());
		return purchaseManager.loadPendingOrder(getCallerName());
	}

	@Override
	public String showCart() {
		return getViewStateMachine().setCartView();
	}





	
	

}
