package src.backingbean;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.User;
import src.inter.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@SessionScoped
public class PurchaseViewBB implements IProcessable, Serializable{
	
	private Order order;
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	@Transactional
	public String purchaseConfirm(){
		System.out.println("remark = " + order.getDeliveryDetails().getRemark());	
		System.out.println("PURCHASEVIEWBB - purchaseConfirm() - " + new Date() + " - order= " + getOrder());		
		System.out.println("remark = " + getOrder().getDeliveryDetails().getRemark());		

		//shoppingFacade.setOrder(getOrder());
		return shoppingFacade.purchaseConfirm();		
	}
	
	public String simulaPagoOK(){		
		return "pagoOK";
	}
	
	public String simulaPagoKO(){		
		return "pagoKO";
	}
	
	public Order getOrder(){
		if(order == null){
			order = shoppingFacade.getOrder();
		}
		return order;
	}
	
	public Cart getCart(){
		return getOrder().getCart();
	}

	
	public void setRemark(String remark){
		System.out.println("PURCHASEVIEWBB - setRemark() - " + new Date() + " - remark antes= " + remark);		

		getOrder().getDeliveryDetails().setRemark(remark);
		System.out.println("PURCHASEVIEWBB - setRemark() - " + new Date() + " - remark despues= " + order.getDeliveryDetails().getRemark());	
		System.out.println("PURCHASEVIEWBB - setRemark() - " + new Date() + " - remark despues= " + getOrder().getDeliveryDetails().getRemark());		

	}
	
	public String getRemark(){
		return getOrder().getDeliveryDetails().getRemark();

	}
	
	public String getDeliveryAddress(){
		return getOrder().getDeliveryDetails().getDeliveryAddress();
	}
	
	public void setDeliveryAddress(String deliveryAddress){
		getOrder().getDeliveryDetails().setDeliveryAddress(deliveryAddress);
	}
	
	public String getDeliveryType(){
		return getOrder().getDeliveryDetails().getDeliveryType();
	}
	
	public void setDeliveryType(String deliveryType){
		getOrder().getDeliveryDetails().setDeliveryType(deliveryType);
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
