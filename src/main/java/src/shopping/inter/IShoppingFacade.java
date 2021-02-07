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

	
	public List<Product> getAvail();	
	public  List<Articulo> getAvail(Product prod);	
	public List<Oferta> getAvail(Articulo articulo);
	public String addItemToCart(Oferta item, Integer numItems);
	public String removeItemFromCart(CartItem item);
	public String resetCart();
	public String configOrder();
	public String preConfirm();
	public String purchaseConfirm();
//	public String createOrder();
	public Cart getCart();
	public Order getOrder();
//	public void setOrder(Order order);

	public String findOrder(Integer order_id);
	public String cancelOrder();
	public String deleteOrder();
	public String setPaymentProcessOK(Boolean result);
	public String showOfertaDetail(Oferta oferta);
	public Oferta getOfertaSeleccionada();
	public String showOrder();
	public String nuevaCompra();
	public String invalidateSession();
	public Boolean isClient();
	public String getCallerName();
	public String changeNumItems(Integer id, Integer numItems);

}
