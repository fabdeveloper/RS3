package src.shopping.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;
import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IPurchaseManager;

@SessionScoped
@DeclareRoles({"CLIENT", "ADMIN"})
@RolesAllowed("CLIENT")
public class PurchaseManager implements IPurchaseManager, Serializable {
	
	static Logger logger = Logger.getLogger(PurchaseManager.class.getName());
	
	@Inject
	private ICartManager cartManager;
	@Inject
	private IServiceLocator serviceLocator;
	
	private Order order;

	


	@Override
	public String createOrder() {
		logger.log(Level.INFO, "PURCHASEMANAGER - createOrder() - " + new Date());
		
		order = serviceLocator.getOrderServices().getGestorE().getFactory().crear();
		setClient();
		setCart();
		order.setConfirmationDate(new Date());
		setPurchaseStatus();
		setDeliveryDetails();

		return "purchaseview";
	}

	@Override
	public String confirm() {
		logger.log(Level.INFO, "PURCHASEMANAGER - confirm() - " + new Date() + " - ORDER= " + order);
	

		if(paymentProcess()){ // OK
			
			serviceLocator.getOrderServices().create(order);
			
		}else{  // PAYMENT ERROR
			throw new RuntimeException("Payment Error");
		}

		return "purchaseview";
	}
	
	private boolean paymentProcess(){
		return true;
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
		cartManager.getCart().setOrder(order);
		order.setCart(cartManager.getCart());
	}

	private void setPurchaseStatus() {
		PurchaseStatus purchaseStatus = serviceLocator.getPurchaseStatusServices()
				.getGestorE().getFactory().crear();
		
		purchaseStatus.setLastModification(new Date());
		purchaseStatus.setRemark("starting");
		
		purchaseStatus.setOrder(order);
		order.setPurchaseStatus(purchaseStatus);		
	}
	
	private void setDeliveryDetails() {
		DeliveryDetails deliveryDetails = serviceLocator.getDeliveryDetailsServices()
				.getGestorE().getFactory().crear();
		
		deliveryDetails.setDeliveryAddress(getClient().getAddress());
		deliveryDetails.setRemark("pendiente");
		deliveryDetails.setDeliveryType("normal");
		
		deliveryDetails.setOrder(order);
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
	public void setOrder(Order neworder) {
		order = neworder;
	}

	@Override
	public Order findOrder(Integer order_id) {
		logger.log(Level.INFO, "PurchaseManager-find: order_id = " + order_id);
		
		order = serviceLocator.getOrderServices().getGestorE().getDao().find(order_id);
		logger.log(Level.INFO, "PurchaseManager-find: encontrada order = " + order);

		return order;
	}

	@Override
	public Order cancelOrder(Integer order_id) {
		logger.log(Level.INFO, "PURCHASEMANAGER - cancelOrder() - " + new Date() + " - ORDER_ID = " + order_id);
		
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



}
