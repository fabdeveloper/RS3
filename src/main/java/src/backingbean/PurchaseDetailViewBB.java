package src.backingbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.DeliveryDetails;
import src.entity.DeliveryDetailsStatusType;
import src.entity.DeliveryType;
import src.entity.Order;
import src.entity.User;
import src.jsfcompslib.util.interfaces.IProcessable;
import src.shopping.inter.IShoppingFacade;

@Named
@SessionScoped
public class PurchaseDetailViewBB implements IProcessable, Serializable {
	

	private static final long serialVersionUID = 1099L;


	static Logger logger = Logger.getLogger(PurchaseDetailViewBB.class.getName());
	
	
	@Inject
	private IShoppingFacade shoppingFacade;	
//	private Order order;	
	private Integer localizador;

	


	public String find(){
		logger.log(Level.INFO, "PurchaseDetailViewBB-find() - localizador = " + localizador);
		
//		order = null;
		return shoppingFacade.findOrder(localizador);
	}

	public String getOrderStatus() {
		return getOrder().getPurchaseStatus().getStatus().name();
	}

	public Integer getOrderId() {
		return getOrder().getId();
	}

	public String getClientName() {
		return getUser().getName();
	}

	public String getClientNick() {
		return getUser().getNick();
	}

	public String getClientAddress() {
		return getUser().getAddress();
	}

	public String getClientEmail() {
		return getUser().getEmail();
	}

	public String getDeliveryAddress() {
		return getDeliveryDetails().getDeliveryAddress();
	}

	public String getDeliveryRemarks() {
		return getDeliveryDetails().getRemark();
	}
	
	public DeliveryType getDeliveryType() {
		return getDeliveryDetails().getDeliveryType();
	}
	
	public DeliveryDetailsStatusType getDeliveryStatus() {
		return getDeliveryDetails().getStatus();
	}

	public String getCartNumItems() {
		return String.valueOf(getCartItemsList().size());
	}

	public String getCartTotalPrice() {
		return String.valueOf(getCart().getValue());
	}

	public List<CartItem> getCartItemsList() {
		return getCart().getListaItems();
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	public Date getConfirmationDate() {
		return getOrder().getConfirmationDate();
	}

	public Integer getLocalizador() {
		return localizador;
	}

	public void setLocalizador(Integer localizador) {
		this.localizador = localizador;
	}

	private Order getOrder() {
//		if(order == null){
//			order = shoppingFacade.getOrder();
//		}
//		return order;
		return shoppingFacade.getOrder();
	}


	private User getUser() {
		return getOrder().getClient();
	}

	private DeliveryDetails getDeliveryDetails() {
		return getOrder().getDeliveryDetails();
	}

	
	private Cart getCart() {
		return getOrder().getCart();
	}

//	@Override
//	public String process(Object obj) {
//		String retorno = "";
//		String id = "0";
//		if(obj instanceof Integer){ id = ((Integer) obj).toString(); }
//		if(obj instanceof String){	id = (String)obj; }	
//		switch(id){
//		case "1":
//			retorno = simulaPagoOk();
//			break;
//		case "2":
//			retorno = simulaPagoKo();
//			break;
//			default:;			
//		}		
//		return retorno;
//	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		PurchaseDetailViewBB.logger = logger;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transactional
	public String simulaPagoOk(){		
		return shoppingFacade.purchaseConfirm();
	}
	
	@Transactional
	public String simulaPagoKo(){		
		return shoppingFacade.paymentError();
//		return "";
	}
	
	@Override
	public String process() {
		return find();
	}
	
	@Transactional
	public String cancelOrder(){
		return shoppingFacade.cancelOrder();
	}
	
	@Transactional
	public String modifyOrder(){
		return shoppingFacade.deleteOrder();
	}
	
	public String nuevaCompra(){
		return shoppingFacade.nuevaCompra();
	}
	
	
	/********************************************************/
	//  LOCALIZATION
	
	/*
	private String getString(String etiqueta) {
		return getShoppingFacade().getString(etiqueta);
	}
	
	public String getStringOrderId() {
		return getString("purchasedetail_order_id");
	}
	
	public String getStringOrderConfirmationDate() {
		return getString("purchasedetail_order_confirmationdate");
	}
	
	public String getStringOrderStatus() {
		return getString("purchasedetail_order_status");
	}
	
	public String getStringUserName() {
		return getString("user_management_name");
	}
	
	public String getStringUserEmail() {
		return getString("user_management_email");
	}
	
	public String getStringUserNick() {
		return getString("user_management_nick");
	}
	
	public String getStringUserAddress() {
		return getString("user_management_address");
	}
	
	public String getStringDeliveryAddress() {
		return getString("configview_delivery_direcciondeenvio");
	}
	
	public String getStringDeliveryRemarks() {
		return getString("configview_delivery_comentarios");
	}
	
	public String getStringDeliveryType() {
		return getString("configview_delivery_tipodeenvio");
	}
	
	public String getStringDeliveryStatus() {
		return getString("purchasedetail_delivery_status");
	}
	
	public String getStringCartNumArticulos() {
		return getString("cartview_total_Numerodearticulos");
	}
	
	public String getStringCartPrice() {
		return getString("cartview_total_Preciototal");
	}
	
	public String getStringFindOrder() {
		return getString("purchasedetailview_buttontext_find_order");
	}
	
	public String getStringButtonTextCancelOrder() {
		return getString("purchasedetailview_buttontext_cancel_order");
	}
	
	public String getStringButtonTextModifyOrder() {
		return getString("purchasedetailview_buttontext_modify_order");
	}
	*/

	
	
	

}
