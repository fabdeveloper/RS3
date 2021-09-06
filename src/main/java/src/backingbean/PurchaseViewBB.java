package src.backingbean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;

import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.DeliveryDetailsStatusType;
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
	
	@Inject
	private IShoppingFacade shoppingFacade;


	private List<DeliveryType> listaDeliveryTypes;
	private List<DeliveryDetailsStatusType> listaDeliveryStatusTypes;

	private Boolean remarksRendered = false;
	private Boolean deliveryTypeDisabled = false;
	private Boolean deliveryStatusDisabled = true;	
	private Boolean lastModificationDateDisabled = true;
	private Boolean lastModificationDateRendered = false;
	
	
	@Interceptors({AuditInterceptor.class})
	@Transactional
	public String purchaseConfirm(){
		return shoppingFacade.purchaseConfirm();		
	}
	
	@Transactional
	@Override
	public String process(){
		return shoppingFacade.preConfirm();
	}
	
	
	@Transactional
	public String updateDeliveryInfo() {
		return getShoppingFacade().preConfirm();		
	}
	
	public String menuEventListener() {
		return null;
	}
	
	
	public String simulaPagoOK(){		
		return "pagoOK";
	}
	
	public String simulaPagoKO(){		
		return "pagoKO";
	}
	
	public Order getOrder(){
		return shoppingFacade.getOrder();
	}
	
	public Cart getCart(){
		return getOrder().getCart();
	}
	
	
	
//	public void setRemark(String remark){
//		getOrder().getDeliveryDetails().setRemark(remark);
//	}
//	
//	public String getRemark(){
//		return getOrder().getDeliveryDetails().getRemark();
//	}
//	
//	public String getDeliveryAddress(){
//		return getOrder().getDeliveryDetails().getDeliveryAddress();
//	}
//	
//	public void setDeliveryAddress(String deliveryAddress){
//		getOrder().getDeliveryDetails().setDeliveryAddress(deliveryAddress);
//	}
//	
//	public DeliveryType getDeliveryType(){
//		return getOrder().getDeliveryDetails().getDeliveryType();
//	}
//	
//	public void setDeliveryType(DeliveryType deliveryType){
//		getOrder().getDeliveryDetails().setDeliveryType(deliveryType);
//	}	
	
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


	
	
	
	public DeliveryDetails getDeliveryDetails() {
		return getOrder().getDeliveryDetails();
	}

	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
		getOrder().setDeliveryDetails(deliveryDetails);
	}


	public List<DeliveryType> getListaDeliveryTypes() {
		if(listaDeliveryTypes == null) {
			listaDeliveryTypes = Arrays.asList(DeliveryType.values());
		}
		return listaDeliveryTypes;
	}

	public void setListaDeliveryTypes(List<DeliveryType> listaDeliveryTypes) {
		this.listaDeliveryTypes = listaDeliveryTypes;
	}

	public List<DeliveryDetailsStatusType> getListaDeliveryStatusTypes() {
		if(listaDeliveryStatusTypes == null) {
			listaDeliveryStatusTypes = Arrays.asList(DeliveryDetailsStatusType.values());
		}
		return listaDeliveryStatusTypes;
	}

	public void setListaDeliveryStatusTypes(List<DeliveryDetailsStatusType> listaDeliveryStatusTypes) {
		this.listaDeliveryStatusTypes = listaDeliveryStatusTypes;
	}



	public Boolean getRemarksRendered() {
		return remarksRendered;
	}

	public void setRemarksRendered(Boolean remarksRendered) {
		this.remarksRendered = remarksRendered;
	}

	public Boolean getDeliveryTypeDisabled() {
		return deliveryTypeDisabled;
	}

	public void setDeliveryTypeDisabled(Boolean deliveryTypeDisabled) {
		this.deliveryTypeDisabled = deliveryTypeDisabled;
	}

	public Boolean getDeliveryStatusDisabled() {
		return deliveryStatusDisabled;
	}

	public void setDeliveryStatusDisabled(Boolean deliveryStatusDisabled) {
		this.deliveryStatusDisabled = deliveryStatusDisabled;
	}

	public Boolean getLastModificationDateDisabled() {
		return lastModificationDateDisabled;
	}

	public void setLastModificationDateDisabled(Boolean lastModificationDateDisabled) {
		this.lastModificationDateDisabled = lastModificationDateDisabled;
	}

	public Boolean getLastModificationDateRendered() {
		return lastModificationDateRendered;
	}

	public void setLastModificationDateRendered(Boolean lastModificationDateRendered) {
		this.lastModificationDateRendered = lastModificationDateRendered;
	}


}
