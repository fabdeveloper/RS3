package src.shopping.inter;

import java.util.List;

import src.entity.Articulo;
import src.entity.Cart;
import src.entity.Oferta;
import src.entity.Order;
import src.entity.Product;

public interface IShoppingFacade {
	
	public List<Product> getAvail();	
	public  List<Articulo> getAvail(Product prod);	
	public List<Oferta> getAvail(Articulo articulo);
	public String addItemToCart(Oferta item);
	public Cart removeItemFromCart(Oferta item);
	public Cart resetCart();
	public String purchaseConfirm();
	public String createOrder();
	public Cart getCart();
	public Order getOrder();
	public void setOrder(Order order);
	public IPurchaseManager getPurchaseManager();
	public String findOrder(Integer order_id);
	public String cancelOrder(Integer order_id);

}
