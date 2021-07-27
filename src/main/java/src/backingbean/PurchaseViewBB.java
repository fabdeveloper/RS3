package src.backingbean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;

import src.entity.Cart;
import src.entity.DeliveryType;
import src.entity.Order;
import src.entity.User;
import src.interceptors.AuditInterceptor;
import src.jsfcompslib.util.interfaces.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@SessionScoped
public class PurchaseViewBB implements IProcessable, Serializable{
	

	private static final long serialVersionUID = 122L;
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
	
	public DeliveryType getDeliveryType(){
		return getOrder().getDeliveryDetails().getDeliveryType();
	}
	
	public void setDeliveryType(DeliveryType deliveryType){
		getOrder().getDeliveryDetails().setDeliveryType(deliveryType);
	}	
	
	public User getUser(){
		return getOrder().getClient();
	}

	
	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transactional
	@Override
	public String process(){
		return shoppingFacade.preConfirm();
	}
	
	private String getString(String etiqueta) {
		return getShoppingFacade().getString(etiqueta);
	}
	
	public String getStringDireccionEnvio() {
		return getString("configview_delivery_direcciondeenvio");
	}
	
	public String getStringComentarios() {
		return getString("configview_delivery_comentarios");
	}
	
	public String getStringTipoEnvio() {
		return getString("configview_delivery_tipodeenvio");
	}
	
	public String getStringActualizarDatosEnvio() {
		return getString("configview_delivery_buttontext_update");
	}
	
	public String getStringUserName() {
		return getString("user_management_name");
	}
	
	public String getStringUserEmail() {
		return getString("user_management_email");
	}
	
	public String getStringPagarConStripe() {
		return getString("purchaseview_buttontext_pagarconStripe");
	}

}
