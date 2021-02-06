package src.service;

/**************************************************************************/
/*	Service Locator
 * 	
 * 	
 * 
 * 	Author : Fabricio Tosi
 */
/**************************************************************************/

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.CartItem;
import src.entity.DeliveryDetails;
import src.entity.Grupo;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.entity.PurchaseStatus;
import src.entity.User;
import src.entityservices.IEntityServices;
import src.inter.IEncripter;
import src.inter.IServiceLocator;
import src.shopping.inter.ISessionManager;
import src.shopping.inter.IShoppingFacade;
import src.shopping.inter.IViewStateMachine;

@Singleton
public class ServiceLocator implements IServiceLocator, Serializable{
	
	@Resource
	private SessionContext ctx;
	
//	private HttpServletRequest httpServletRequest;
	
	@PersistenceContext(unitName="RS3_PU")
	private EntityManager em;
	
	@Inject
	private IEncripter encripter;
	@Inject
	private IEntityServices<Grupo> grupoServices;
	@Inject
	private IEntityServices<User> userServices;
	@Inject
	private IEntityServices<Product> productServices;
	@Inject
	private IEntityServices<Articulo> articuloServices;
	@Inject
	private IEntityServices<Oferta> ofertaServices;
	@Inject 
	private IEntityServices<Cart> cartServices;
	@Inject
	private IEntityServices<CartItem> cartItemServices;
	@Inject 
	private IEntityServices<Order> orderServices;
	@Inject 
	private IEntityServices<PurchaseStatus> purchaseStatusServices;
	@Inject 
	private IEntityServices<DeliveryDetails> deliveryDetailsServices;
	

	@Inject 
	private IShoppingFacade shoppingFacade;
	
	@Override 
	public SessionContext getSessionContext(){
		return ctx;
	}
	
	@Override
	public EntityManager getEntityManager(){
		return em;
	}

	@Override
	public IEncripter getEncripter() {
		return encripter;
	}
	
	@Override
	public IEntityServices<Grupo> getGrupoServices(){
		return grupoServices;
	}
	
	@Override
	public IEntityServices<User> getUserServices() {
		return userServices;
	}

	@Override
	public IEntityServices<Product> getProductServices() {
		return productServices;
	}

	@Override
	public IEntityServices<Articulo> getArticuloServices() {
		return articuloServices;
	}
	
	@Override
	public IEntityServices<Oferta> getOfertaServices() {
		return ofertaServices;
	}
	
	@Override
	public IEntityServices<Cart> getCartServices() {
		return cartServices;
	}
	
	@Override
	public IEntityServices<CartItem> getCartItemServices() {
		return cartItemServices;
	}

	@Override
	public IEntityServices<Order> getOrderServices() {
		return orderServices;
	}

	@Override
	public IEntityServices<PurchaseStatus> getPurchaseStatusServices() {
		return purchaseStatusServices;
	}

	@Override
	public IEntityServices<DeliveryDetails> getDeliveryDetailsServices() {
		return deliveryDetailsServices;
	}


	@Override
	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}


}
