package src.shopping.impl;

import java.io.Serializable;
import java.util.Date;

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
	
//	@Inject
//	private SecurityContext sc;
	
//	@Resource
//	private EJBContext securityContext;
	
//	@Resource
//	private SessionContext securityContext;
	
	@Inject
	private ICartManager cartManager;
	
	private Order order;

	
	@Inject
	private IServiceLocator serviceLocator;

	@Override
	public Order createOrder() {
		System.out.println("PURCHASEMANAGER - createOrder() - " + new Date());		
		
		order = serviceLocator.getOrderServices().getGestorE().getFactory().crear();
		setClient();
		setCart();
		order.setConfirmationDate(new Date());
		setPurchaseStatus();
		setDeliveryDetails();

		return order;
	}

	@Override
	public Order confirm() {
		
		if(paymentProcess()){ // OK
			
			serviceLocator.getOrderServices().create(order);
			
		}else{  // PAYMENT ERROR
			throw new RuntimeException("Payment Error");
		}

		return order;
	}
	
	private boolean paymentProcess(){
		return true;
	}

	
	private void setClient() {
//		String clientNick = securityContext.getCallerPrincipal().getName();
//		User user = serviceLocator.getUserServices().getGestorE().getDao()
//				.createNamedQuery("byNick", "nick", clientNick);
//		
//		order.setClient(user);
	}
	
	private void setCart() {
		order.setCart(cartManager.getCart());
	}

	public void setPurchaseStatus() {
		PurchaseStatus purchaseStatus = serviceLocator.getPurchaseStatusServices()
				.getGestorE().getFactory().crear();
		
		purchaseStatus.setLastModification(new Date());
		purchaseStatus.setRemark("starting");
		purchaseStatus.setOrder(order);
		order.setPurchaseStatus(purchaseStatus);		
	}


	public void setDeliveryDetails() {
		DeliveryDetails deliveryDetails = serviceLocator.getDeliveryDetailsServices()
				.getGestorE().getFactory().crear();
		
		deliveryDetails.setDeliveryAddress(getClient().getAddress());
		deliveryDetails.setOrder(order);
		deliveryDetails.setRemark("pendiente");
		deliveryDetails.setDeliveryType("normal");
		
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



}
