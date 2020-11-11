package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.User;
import src.inter.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class PurchaseViewBB implements IProcessable{
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	
	public void purchaseConfirm(){
		shoppingFacade.purchaseConfirm();		
	}
	
	public Order getOrder(){
		return shoppingFacade.getOrder();
	}
	
	public Cart getCart(){
		return getOrder().getCart();
	}
	
	public DeliveryDetails getDeliveryDetails(){
		return getOrder().getDeliveryDetails();
	}
	
	public User getUser(){
		return getOrder().getClient();
	}

	@Override
	public String process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
