package src.shopping.inter;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;

public interface IPurchaseManager {
	
	public Order createOrder();
	public Order confirm();
	public void setClient(User client);
	public User getClient();
	public Cart getCart();
	public void setCart(Cart cart);
	public void setStatus(PurchaseStatus status);
	public PurchaseStatus getStatus();
	public void setDeliveryDetails(DeliveryDetails deliverydetails);
	public DeliveryDetails getDeliveryDetails();

}
