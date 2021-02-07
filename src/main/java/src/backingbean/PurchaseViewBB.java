package src.backingbean;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Order;
import src.entity.User;
import src.inter.IProcessable;
import src.interceptors.AuditInterceptor;
import src.shopping.inter.IShoppingFacade;


@Named
@SessionScoped
public class PurchaseViewBB implements IProcessable, Serializable{
	
	//private Order order;
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	@Interceptors({AuditInterceptor.class})
	@Transactional
	public String purchaseConfirm(){
		return shoppingFacade.purchaseConfirm();		
	}
	
	public String simulaPagoOK(){		
		return "pagoOK";
	}
	
	public String simulaPagoKO(){		
		return "pagoKO";
	}
	
	public Order getOrder(){
//		if(order == null){
//			order = shoppingFacade.getOrder();
//		}
//		return order;
		return shoppingFacade.getOrder();
	}
	
	public Cart getCart(){
		return getOrder().getCart();
	}
	
	public void setRemark(String remark){
		getOrder().getDeliveryDetails().setRemark(remark);
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

//	@Override
//	public String process(Object obj) {
//		return shoppingFacade.showOrder();
//	}
	
	@Transactional
	@Override
	public String process(){
		return shoppingFacade.preConfirm();
	}

}
