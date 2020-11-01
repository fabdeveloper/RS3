package src.shopping.impl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;
import src.inter.IServiceLocator;
import src.shopping.inter.IPurchaseManager;

@SessionScoped
public class PurchaseManager implements IPurchaseManager {
	
	private Order order;
	private User owner;
	private Cart purchaseCart;
	
	@Inject
	private IServiceLocator serviceLocator;

	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		return order;
	}

	@Override
	public Order confirm() {
		// TODO Auto-generated method stub
		return order;
	}

	@Override
	public void setClient(User client) {
		owner = client;		
	}

	@Override
	public User getClient() {
		return owner;
	}

	@Override
	public Cart getCart() {
		return purchaseCart;
	}

	@Override
	public void setCart(Cart cart) {
		purchaseCart = cart;
	}

	@Override
	public void setStatus(PurchaseStatus status) {
		// TODO Auto-generated method stub

	}

	@Override
	public PurchaseStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDeliveryDetails(DeliveryDetails deliverydetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public DeliveryDetails getDeliveryDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
