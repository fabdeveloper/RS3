package src.shopping.inter;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;

public interface IPurchaseManager {
	
	public Order createOrder();
	public Order confirm();
	public User getClient();
	public Cart getCart();
	public PurchaseStatus getPurchaseStatus();
	public DeliveryDetails getDeliveryDetails();
	public Order getOrder();

}
