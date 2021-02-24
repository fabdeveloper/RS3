package src.shopping.inter;

import java.util.List;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;
import src.inter.IServiceLocator;

public interface IShoppingFacade {
	
	public IServiceLocator getServiceLocator();

	public IPurchaseManager getPurchaseManager();
	public ICartManager getCartManager();
	public IStockManager getStockManager();
	public IViewStateMachine getViewStateMachine();
	public ISessionManager getSessionManager();
	public ILocationManager getLocationManager();

	
	public List<Product> getAvail();	
	public  List<Articulo> getAvail(Product prod);	
	public List<Oferta> getAvail(Articulo articulo);
	
	public String addItemToCart(Oferta item, Integer numItems);
	public String removeItemFromCart(CartItem item);
	public String resetCart();
	public Cart getCart();
	public String showCart();

	public String setPaymentProcessOK(Boolean result);
	public String showOfertaDetail(Oferta oferta);
	public Oferta getOfertaSeleccionada();
	public String nuevaCompra();
	public String changeNumItems(Integer id, Integer numItems);
	
	// StockManagement
	public Boolean consumirStock(Integer oferta_id, Integer unidades);
	public Boolean recuperarStock(Integer oferta_id, Integer unidades);

	// PurchaseManagement
	public String configOrder();
	public String preConfirm();
	public String purchaseConfirm();
	public Order getOrder();
	public String showOrder();
	public String findOrder(Integer order_id);
	public String cancelOrder();
	public String deleteOrder();
	public String paymentError();
	public Boolean loadPendingOrder();

	// SessionManagement
	public String login(String user, String password);
	public String logout();
	public String invalidateSession();
	public Boolean isClient();
	public String getCallerName();


	// LocationManager
	public String getString(String prop);

}
