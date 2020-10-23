package src.inter;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.DeliveryDetails;
import src.entity.Grupo;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.entity.PurchaseStatus;
import src.entity.User;
import src.entityservices.IEntityServices;

public interface IServiceLocator {
	
	public SessionContext getSessionContext();
	
	public EntityManager getEntityManager();
	
	public IEntityServices<Grupo> getGrupoServices();
	
	public IEntityServices<User> getUserServices();
	
	public IEntityServices<Product> getProductServices();

	public IEntityServices<Articulo> getArticuloServices();

	public IEntityServices<Oferta> getOfertaServices();
	
	public IEntityServices<Cart> getCartServices();
	
	public IEntityServices<Order> getOrderServices();

	public IEntityServices<PurchaseStatus> getPurchaseStatusServices();

	public IEntityServices<DeliveryDetails> getDeliveryDetailsServices();


	

}
