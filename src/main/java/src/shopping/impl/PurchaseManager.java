package src.shopping.impl;


import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.DeliveryDetailsStatusType;
import src.entity.DeliveryType;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.PurchaseStatusType;
import src.entity.User;
import src.exception.DBException;
import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IPurchaseManager;
import src.shopping.inter.IShoppingFacade;

@SessionScoped
public class PurchaseManager implements IPurchaseManager, Serializable {
	

	private static final long serialVersionUID = 112L;


	static Logger logger = Logger.getLogger(PurchaseManager.class.getName());
	

	@Inject
	private IServiceLocator serviceLocator;
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private Order order;
	private Boolean paymentProcessOK = true;

	

	private void publish(String msg) {
		publishStandard(msg);
		publishFaces(msg);
	}
	
	private void publishStandard(String msg) {
		System.out.println(msg);
	}
	private void publishFaces(String msg) {
		FacesContext.getCurrentInstance().addMessage(msg, new FacesMessage(msg));
	}

	private ICartManager getCartManager() {
		return getShoppingFacade().getCartManager();
	}

	@Override
	public String createOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - createOrder() - " + new Date());
		
		order = serviceLocator.getOrderServices().getGestorE().getFactory().crear();
		order.setLastModificationDate(new Date());
		order.setCreationDate(new Date());
		setClient();
		setCart();
		setPurchaseStatus(); 
		setDeliveryDetails();

		return "";
//		return serviceLocator.getViewStateMachine().setConfigView();
	}
	
	@Override
	public void updateOrder(){
		
		if(getCartManager().isCartEmpty()){// empty cart
			System.out.println("PurchaseManager.updateOrder() - empty cart");
			
			deleteOrder();
			
		}else if(order == null){// if order is null (first item to cart)
			System.out.println("PurchaseManager.updateOrder() - order == null");

			createOrder();
			order.setCreationDate(new Date());
			persistOrder();
			
		}else{ // add modif remove  item(not empty cart)
			System.out.println("PurchaseManager.updateOrder() - add or modify cart");

			order.setLastModificationDate(new Date());
			mergeOrder();
			
			getCartManager().setCart(order.getCart());
			
		}
		System.out.println("ORDER :  *********************");
		if(order != null)System.out.println(order.toString());

	}
	

	@Override
	public Boolean confirm() {
		Boolean ok = true;
		logger.log(Level.INFO, "PURCHASEMANAGER - confirm() - " + new Date() + " - ORDER= " + order);
	
		order.getPurchaseStatus().setStatus(PurchaseStatusType.CONFIRMADO);
		order.getPurchaseStatus().setLastModification(new Date());
		order.setLastModificationDate(new Date());
		order.setConfirmationDate(new Date());
		
		try{
			mergeOrder();
			
		}catch(Throwable t){
			throw new DBException("CONFIRMATION ERROR - order_id = " + order.getId() , t);
			
		}

//		if(isPaymentProcessOK()){ // OK
//			
//			order.getPurchaseStatus().setRemark("CONFIRMADO");
//			order.setLastModificationDate(new Date());
//			order.setConfirmationDate(new Date());
//
//			mergeOrder();
//			
//			
//		}else{  // PAYMENT ERROR
//			ok = false;
//			throw new RuntimeException("Payment Error");
//		}

		return ok;
	}
	
	private void persistOrder(){
		System.out.println("PurchaseManager.persistOrder()");

		order.setLastModificationDate(new Date());
		serviceLocator.getOrderServices().persist(order); // graba la orden en DB
		flush();
	}
	
	private void mergeOrder(){
		System.out.println("PurchaseManager.mergeOrder()");

		order.setLastModificationDate(new Date());
		order = serviceLocator.getOrderServices().merge(order); // graba la orden en DB	
		flush();
	}
	
	private void flush(){
		serviceLocator.getEntityManager().flush();
	}
	
	
	@Override
	public Boolean getPaymentProcessOK() {
		return paymentProcessOK;
	}

	@Override
	public void setPaymentProcessOK(Boolean paymentProcessOK) {
		this.paymentProcessOK = paymentProcessOK;
	}

	private boolean refundProcess(){
		return true;
	}
	
	private void setClient() {
		String clientNick = serviceLocator.getSessionContext().getCallerPrincipal().getName();

		User user = serviceLocator.getUserServices().getGestorE().getDao()
				.createNamedQuery("byNick", "nick", clientNick);
		
		order.setClient(user);

	}
	
	private void setCart() {
		order.setCart(getCartManager().getCart());
	}

	private void setPurchaseStatus() {
		PurchaseStatus purchaseStatus = serviceLocator.getPurchaseStatusServices()
				.getGestorE().getFactory().crear();
		
		purchaseStatus.setLastModification(new Date());
		purchaseStatus.setStatus(PurchaseStatusType.NO_CONFIRMADO);
		
		order.setPurchaseStatus(purchaseStatus);		
	}
	
	private void setDeliveryDetails() {
		DeliveryDetails deliveryDetails = serviceLocator.getDeliveryDetailsServices()
				.getGestorE().getFactory().crear();
		
		deliveryDetails.setDeliveryAddress(getClient().getAddress());
		deliveryDetails.setStatus(DeliveryDetailsStatusType.PENDIENTE);
		deliveryDetails.setDeliveryType(DeliveryType.NORMAL);
		deliveryDetails.setLastModificationDate(new Date());
		
		order.setDeliveryDetails(deliveryDetails);		
	}

	@Override
	public User getClient() {
		return order.getClient();
	}

	@Override
	public Cart getCart() {
		return order.getCart();
	}

	@Override
	public PurchaseStatus getPurchaseStatus() {
		return order.getPurchaseStatus();
	}

	@Override
	public DeliveryDetails getDeliveryDetails() {
		return order.getDeliveryDetails();
	}

	@Override
	public Order getOrder() {
		return order;
	}


	@Override
	public Order findOrder(Integer order_id) {
//		logger.log(Level.INFO, "PurchaseManager-find: order_id = " + order_id);		
		order = serviceLocator.getOrderServices().getGestorE().getDao().find(order_id);
//		logger.log(Level.INFO, "PurchaseManager-find: encontrada order = " + order);
		return order;
	}

	@Override
	public Order cancelOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - cancelOrder() - " + new Date() + " - ORDER_ID = " + order.getId());
		
		try{
			if(isCancelable()){
				order.getPurchaseStatus().setStatus(PurchaseStatusType.CANCELADO);
				order.getPurchaseStatus().setLastModification(new Date());
				// graba la cancelacion
				serviceLocator.getOrderServices().merge(order);
				// recupera el stock
				getCart().getListaItems()
				.forEach(i->getShoppingFacade()
					.recuperarStock(i.getOferta().getId(), i.getCounter()));
				
				// inicia el proceso de devolucion
				if(!refundProcess()){
					throw new Exception("Refund ERROR");
				}			
			}
			serviceLocator.getOrderServices().getGestorE().getDao().getEntityManager().flush();
			
		}catch(Throwable e){
			String msg = "Rollback transaction : ORDER = " + order.getId() + " - msg= " + e.getMessage();
			logger.log(Level.SEVERE, msg);
			publish(msg);
			serviceLocator.getSessionContext().setRollbackOnly();
//			order.getPurchaseStatus().setRemark("NOT CANCELLED");
//			order.getPurchaseStatus().setStatus(PurchaseStatusType.n);pppppppppppppppppppppppp
//			order.getPurchaseStatus().setLastModification(new Date());
		}
		return order;
	}
	
	private boolean isCancelable(){
		boolean bcancelable = true;
		PurchaseStatusType status = order.getPurchaseStatus().getStatus();
		switch(status) {
		case CONFIRMADO:
			bcancelable = false;
			break;
		case CERRADO:
			bcancelable = false;
			break;
			default:
				break;
		}
		return bcancelable;
	}

	@Override
	public Order deleteOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - deleteOrder() - " + new Date() + " - ORDER_ID = " + order.getId());

		serviceLocator.getOrderServices().getGestorE().getDao().remove(order);
		flush();
	
		reset();
		return order;
	}

	@Override
	public void reset() {
		this.order = null;
	}

	@Override
	public Boolean preConfirmation() {
		Boolean ok = true;
		
		order.setLastModificationDate(new Date());
		order.getPurchaseStatus().setStatus(PurchaseStatusType.PRE_CONFIRMADO);
		order.getPurchaseStatus().setLastModification(new Date());
		
		try{ 	
			// grabar preconfirmacion
			mergeOrder();
			// consumir stock
			getCart().getListaItems().forEach(i -> getShoppingFacade().
					consumirStock(i.getOferta().getId(), i.getCounter()));
		
		}catch(Throwable t){
//			order.getPurchaseStatus().setStatus(PurchaseStatusType.NO_CONFIRMADO); 
			throw new DBException("preconfirmation DB error - msg= " + t.getMessage());				
		}		
		return ok;
	}

	@Override
	public void paymentError() {		
		order.setLastModificationDate(new Date());
		order.getPurchaseStatus().setStatus(PurchaseStatusType.PAYMENT_ERROR);
		order.getPurchaseStatus().setLastModification(new Date());
		
		try{ 
			// actualiza status		
			mergeOrder();
			// recupera stock
			getCart().getListaItems()
				.forEach(i->getShoppingFacade()
					.recuperarStock(i.getOferta().getId(), i.getCounter()));
		
		}catch(Throwable t){
			throw new DBException("Error updating order on PAYMENT-ERROR - msg= " + t.getMessage());				
		}
		
	}

	@Override
	public Boolean loadPendingOrder(String user_nick) {
		Boolean result = true;
		try{
			order = serviceLocator.getOrderServices().createNamedQuery("loadPendingOrder", "client_nick", user_nick );
		}catch(Throwable t){
			result = false;
			String msgError = "PurchaseManager.loadPendingOrder() - No se ha cargado ninguna orden - msg= " + t.getMessage();
			publish(msgError);
		}
		if(result)getCartManager().setCart(order.getCart());
		String msg = "PurchaseManager.loadPendingOrder() - result = " + result;
		publish(msg);
		
		return result;
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





}




