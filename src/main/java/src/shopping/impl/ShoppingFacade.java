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

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import localizationUtility.src.inter.ILocalizationManager;
import src.entity.Articulo;
import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.exception.RS3Exception;
import src.inter.IServiceLocator;
import src.manager.IStockManager;
import src.shopping.inter.IAvailabilityManager;
import src.shopping.inter.ICartManager;
import src.shopping.inter.ISessionManager;
import src.shopping.inter.IShoppingFacade;
import src.shopping.inter.IPurchaseManager;
import src.shopping.inter.IViewStateMachine;

@SessionScoped
public class ShoppingFacade implements IShoppingFacade, Serializable{
	
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ShoppingFacade.class.getName());
	
	@Inject 
	private IServiceLocator serviceLocator;
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
	@Inject
	private ILocalizationManager locationManager;
	
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
			getLogger().log(Level.ALL, "error en pre-confirmacion - " + t.getMessage());
//			publish("error en pre-confirmacion - " + t.getMessage());

			result = viewStateMachine.setErrorView();
//			 setRollbackOnly
			serviceLocator.getSessionContext().setRollbackOnly();			
		}		
		return result;
	}

	@Override
	public String purchaseConfirm() {
		String result = viewStateMachine.setOrdersView();
		try{
			purchaseManager.confirm();
			nuevaCompra();

		}catch(Throwable t){
			getLogger().log(Level.ALL, "error en confirmacion - " + t.getMessage());
			result = viewStateMachine.setErrorView();			
		}		
		return result;
	}

	@Override
	public String configOrder() {
		return viewStateMachine.setConfigView();
	}

	@Override
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
		String dispatch = viewStateMachine.setOrderView();
		getLogger().log(Level.INFO, "ShoppingFacade-findOrder - order_id = " + order_id);
		Order order = null;
		try{
			order = purchaseManager.findOrder(order_id);
			if(order == null){
				throw new RS3Exception("Pedido no encontrado nº " + order_id);
			}
		}catch(Throwable t){
			logAndMessage(t);
			dispatch =  null;
		}

		
		getLogger().log(Level.INFO, "ShoppingFacade-findOrder - encontrada order = " + order);

		return dispatch;
	}
	
	private void logAndMessage(Throwable t){
		// log
		String msg = "mensage de error = " + t.getMessage();
		getLogger().log(Level.ALL, msg);
		// message
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		
	}
	
	@Override
	public void publish(String msg) {
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		getLogger().log(Level.INFO, msg);
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
	public String showOrders() {
		return viewStateMachine.setOrdersView();
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
//		System.out.println("ShoppingFacade - loadPendingOrder() - " + new Date());
		return purchaseManager.loadPendingOrder(getCallerName());
	}

	@Override
	public String showCart() {
		return getViewStateMachine().setCartView();
	}

	@Override
	public String login(String user, String password) {
		try{
			sessionManager.login(user, password);			
		}catch(Throwable t){
			// grabar logs
			getLogger().log(Level.ALL, "ShoppingFacade.login() " + new Date());
			// mostrar FacesMessage
		}
		return null;
	}

	@Override
	public String logout() {
		try{
			sessionManager.logout();
		}catch(Throwable t){
			// grabar logs
			getLogger().log(Level.ALL, "ShoppingFacade.logout() " + new Date() + " , msg= " + t.getMessage());
			// mostrar FacesMessage
//			String stringmsg = "Logout error";
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(stringmsg));
		}
		
		return invalidateSession();
	}

	
	@Override
	public ILocalizationManager getLocationManager() {
		return locationManager;
	}

	@Override
	public String getString(String prop) {
		return getLocationManager().getString(prop);
	}
	

	@Override
	public List<Product> getDepartmentStoreList() {
		return getAvail();
	}

	@Override
	public List<Articulo> getArticulosByProductName(String prod_name) {
		return availabilityManager.getArticulosByProductName(prod_name);
	}

	@Override
	public String getOfertasByArticuloName(String arti_name) {
		availabilityManager.getOfertasByArticuloName(arti_name);
		return viewStateMachine.setAvailabilityView();
	}

	@Override
	public String showAvail(String articuloname) {
		availabilityManager.searchName(articuloname);
//		publish("articulos encontrados = " + availabilityManager.getQueryManager().getList().size());

		return viewStateMachine.setAvailabilityView();
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ShoppingFacade.logger = logger;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public void setStockManager(IStockManager stockManager) {
		this.stockManager = stockManager;
	}

	public void setViewStateMachine(IViewStateMachine viewStateMachine) {
		this.viewStateMachine = viewStateMachine;
	}

	public void setSessionManager(ISessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setLocationManager(ILocalizationManager locationManager) {
		this.locationManager = locationManager;
	}
	
	
	





	
	

}
