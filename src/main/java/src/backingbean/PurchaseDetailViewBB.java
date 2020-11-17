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
import src.entity.DeliveryDetails;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.PurchaseStatus;
import src.entity.User;
import src.inter.IProcessable;
import src.service.ServiceLocator;
import src.shopping.inter.IShoppingFacade;

@Named
@SessionScoped
public class PurchaseDetailViewBB implements IProcessable, Serializable {
	
	static Logger logger = Logger.getLogger(PurchaseDetailViewBB.class.getName());
	
	
	@Inject
	private IShoppingFacade shoppingFacade;	
	private Order order;	
	private Integer localizador;

	


	public String find(){
		logger.log(Level.INFO, "PurchaseDetailViewBB-find() - localizador = " + localizador);
		
		order = null;
		return shoppingFacade.findOrder(localizador);
	}

	public String getOrderStatus() {
		return getOrder().getPurchaseStatus().getRemark();
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
	
	public String getDeliveryType() {
		return getDeliveryDetails().getDeliveryType();
	}

	public String getCartNumItems() {
		return String.valueOf(getCartItemsList().size());
	}

	public String getCartTotalPrice() {
		return String.valueOf(getCart().getValue());
	}

	public List<Oferta> getCartItemsList() {
		return getCart().getListaOfertas();
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
		if(order == null){
			order = shoppingFacade.getOrder();
		}
		return order;
	}

	private void setOrder(Order order) {
		this.order = order;
	}

	private User getUser() {
		return getOrder().getClient();
	}

	private DeliveryDetails getDeliveryDetails() {
		return getOrder().getDeliveryDetails();
	}

	private PurchaseStatus getPurchaseStatus() {
		return getOrder().getPurchaseStatus();
	}
	
	private Cart getCart() {
		return getOrder().getCart();
	}

	@Override
	public String process(Object obj) {
		return null;
	}
	
	@Override
	public String process() {
		return find();
	}
	
	@Transactional
	public String cancelOrder(){
		return shoppingFacade.cancelOrder(localizador);
	}
	
	@Transactional
	public String modifyOrder(){
		return shoppingFacade.deleteOrder(order);
	}
	
	

}
