package src.shopping.inter;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;

public interface IPurchaseManager {
	
	public String createOrder();
	public String confirm();
	public User getClient();
	public Cart getCart();
	public PurchaseStatus getPurchaseStatus();
	public DeliveryDetails getDeliveryDetails();
	public Order getOrder();
	public void setOrder(Order neworder);
	public Order findOrder(Integer order_id);
	public Order cancelOrder(Integer order_id);
	public Order deleteOrder(Order order);
	public Boolean getPaymentProcessOK();
	public void setPaymentProcessOK(Boolean result);

}
