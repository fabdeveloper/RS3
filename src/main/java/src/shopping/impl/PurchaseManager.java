package src.shopping.impl;


import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;
import src.exception.DBException;
import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IPurchaseManager;

@SessionScoped
//@DeclareRoles({"CLIENT", "ADMIN"})
//@RolesAllowed("CLIENT")
public class PurchaseManager implements IPurchaseManager, Serializable {
	
	static Logger logger = Logger.getLogger(PurchaseManager.class.getName());
	

	@Inject
	private IServiceLocator serviceLocator;
	
	private Order order;
	private Boolean paymentProcessOK = true;

	


	private ICartManager getCartManager() {
		return serviceLocator.getShoppingFacade().getCartManager();
	}

	@Override
	public String createOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - createOrder() - " + new Date());
		
		order = serviceLocator.getOrderServices().getGestorE().getFactory().crear();
		order.setLastModificationDate(new Date());
		order.setCreationDate(new Date());
		setClient();
		setCart();
		setPurchaseStatus(); // TODO : revisar PurchaseStatus
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
	
		order.getPurchaseStatus().setRemark("CONFIRMADO");
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
		serviceLocator.getOrderServices().create(order); // graba la orden en DB
		flush();
	}
	
	private void mergeOrder(){
		System.out.println("PurchaseManager.mergeOrder()");

		order.setLastModificationDate(new Date());
		order = serviceLocator.getOrderServices().update(order); // graba la orden en DB	
		flush();
	}
	
	private void flush(){
		serviceLocator.getEntityManager().flush();
	}
	
	private void refresh(){
		serviceLocator.getEntityManager().refresh(order);
	}
	
	private boolean isPaymentProcessOK(){
		return paymentProcessOK;
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
		logger.log(Level.INFO, "PURCHASE MANAGER - " + new Date() + " - setClient() - clientNick = " + clientNick);
//		if(clientNick.matches("ANONYMOUS") || clientNick == null || clientNick.length() < 1){
//			User user = serviceLocator.getUserServices().getGestorE().getFactory().crear();
//			user.setAddress("NO INICIADO");
//			user.setEmail("NO INICIADO");
//			user.setName("NO INICIADO");
//			user.setNick(clientNick);
//			
//			order.setClient(user);
//		}else{
			User user = serviceLocator.getUserServices().getGestorE().getDao()
					.createNamedQuery("byNick", "nick", clientNick);
			
			order.setClient(user);
//		}

	}
	
	private void setCart() {
//		cartManager.getCart().setOrder(order);
		order.setCart(getCartManager().getCart());
	}

	private void setPurchaseStatus() {
		PurchaseStatus purchaseStatus = serviceLocator.getPurchaseStatusServices()
				.getGestorE().getFactory().crear();
		
		purchaseStatus.setLastModification(new Date());
		purchaseStatus.setRemark("NO CONFIRMADO");
		
//		purchaseStatus.setOrder(order);
		order.setPurchaseStatus(purchaseStatus);		
	}
	
	private void setDeliveryDetails() {
		DeliveryDetails deliveryDetails = serviceLocator.getDeliveryDetailsServices()
				.getGestorE().getFactory().crear();
		
		deliveryDetails.setDeliveryAddress(getClient().getAddress());
		deliveryDetails.setStatus("pendiente");
		deliveryDetails.setDeliveryType("normal");
		deliveryDetails.setLastModificationDate(new Date());
		
//		deliveryDetails.setOrder(order);
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
		logger.log(Level.INFO, "PurchaseManager-find: order_id = " + order_id);
		
		order = serviceLocator.getOrderServices().getGestorE().getDao().find(order_id);
		logger.log(Level.INFO, "PurchaseManager-find: encontrada order = " + order);

		return order;
	}

	@Override
	public Order cancelOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - cancelOrder() - " + new Date() + " - ORDER_ID = " + order.getId());
		
		try{
			if(isCancelable()){
				order.getPurchaseStatus().setRemark("CANCELLED");
				serviceLocator.getOrderServices().update(order);
				if(!refundProcess()){
					throw new Exception("Refund ERROR");
				}			
			}
			serviceLocator.getOrderServices().getGestorE().getDao().getEntityManager().flush();
			
		}catch(Exception e){
			logger.log(Level.SEVERE, "Rollback transaction : ORDER = " + order.getId() + " - " + e.getMessage() + " - " + e.getStackTrace());
			serviceLocator.getSessionContext().setRollbackOnly();
			order.getPurchaseStatus().setRemark("NOT CANCELLED");
		}
		return order;
	}
	
	private boolean isCancelable(){
		return true;
	}

	@Override
	public Order deleteOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - deleteOrder() - " + new Date() + " - ORDER_ID = " + order.getId());

		serviceLocator.getOrderServices().getGestorE().getDao().remove(order);
		flush();
//		order.getPurchaseStatus().setRemark("DELETED");
//		setOrder(order);	
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
		
		// consumir stock
		getCart().getListaItems().forEach(i -> serviceLocator.getShoppingFacade().
				consumirStock(i.getOferta().getId(), i.getCounter()));

		
		try{ // grabar preconfirmacion			
					
			order.setLastModificationDate(new Date());
			order.getPurchaseStatus().setRemark("PRE-CONFIRMADO");
			mergeOrder();
		
		}catch(Throwable t){
			order.getPurchaseStatus().setRemark("NO-CONFIRMADO");
			throw new DBException("preconfirmation DB error", t);				
		}		
		return ok;
	}

	@Override
	public void paymentError() {		
		order.setLastModificationDate(new Date());
		order.getPurchaseStatus().setRemark("PAYMENT-ERROR");
		// actualiza status		
		try{ 	
			mergeOrder();
		
		}catch(Throwable t){
			throw new DBException("Error updating order on PAYMENT-ERROR", t);				
		}
		// recupera stock
		getCart().getListaItems()
			.forEach(i->serviceLocator.getShoppingFacade()
				.recuperarStock(i.getOferta().getId(), i.getCounter()));
		
	}





}


//@Override
//public List<RuntimeException> preConfirmation() {
//	List<CartItem> listaConsumidos = new ArrayList<CartItem>();
//	List<RuntimeException> listaException = new ArrayList<RuntimeException>();
//	
//	Boolean ok = true;
//	try{ // consumir stock			
//		for(CartItem item : getCart().getListaItems()){
//			boolean btemp = serviceLocator.getShoppingFacade().consumirStock(item.getOferta().getId(), item.getCounter());
//			
//			if(btemp){listaConsumidos.add(item);}
//			else{ 
//				ok = false; // error de stock
//				listaException.add(new ConsumeStockException("insufficient stock")
//						.setCartItem(item)
//						.setOferta(serviceLocator.getShoppingFacade().getStockManager().getOferta()));					
//			}
//		}
//	}catch(Throwable t){
//		ok = false;
//		// DBException
//		listaException.add(new DBException("error consuming stock", t));			
//	}
//	try{ // grabar preconfirmacion			
//		if(ok){				
//			order.setLastModificationDate(new Date());
//			order.getPurchaseStatus().setRemark("PRE-CONFIRMADO");
//			mergeOrder();
//		}
//	}catch(Throwable t){
//		ok = false;
//		// DBException
//		listaException.add(new DBException("preconfirmation DB error", t));				
//	}
//	if(!ok){ // recuperar stock consumido
//		for(CartItem item : listaConsumidos){
//			try{
//				serviceLocator.getShoppingFacade().recuperarStock(
//						item.getOferta().getId(),
//						item.getCounter());
//				
//			}catch(Throwable t){
//				listaException.add(new RecuperaStockException("insufficient stock")
//				.setCartItem(item)
//				.setOferta(serviceLocator.getShoppingFacade().getStockManager().getOferta()));						
//			}
//		}			
//	}		
//	return listaException;
//}

