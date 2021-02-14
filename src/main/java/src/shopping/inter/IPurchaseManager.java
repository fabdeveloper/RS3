package src.shopping.inter;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;

public interface IPurchaseManager {
	
	public String createOrder();
	public void updateOrder();
	public Boolean preConfirmation();
	public Boolean confirm();
	public User getClient();
	public Cart getCart();
	public PurchaseStatus getPurchaseStatus();
	public DeliveryDetails getDeliveryDetails();
	public Order getOrder();
	public Order findOrder(Integer order_id);
	public Boolean loadPendingOrder(String user_nick);
	public Order cancelOrder();
	public Order deleteOrder();
	public Boolean getPaymentProcessOK();
	public void setPaymentProcessOK(Boolean result);
	public void reset();
	public void paymentError();

}
