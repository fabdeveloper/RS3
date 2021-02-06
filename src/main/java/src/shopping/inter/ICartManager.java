package src.shopping.inter;

import java.util.List;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.inter.IServiceLocator;

public interface ICartManager {
	
	public IServiceLocator getServiceLocator();
	public Cart addItem(Oferta item, Integer numItems);
	public Cart removeItem(CartItem item);
	public Cart modifyItem(Integer id, Integer numItems);
	public CartItem getItemById(Integer id);
	public Cart reset();
	public Cart getCart();
	public void setCart(Cart cart);
	public Boolean isCartEmpty();
//	public Boolean validate();
//	public List<Error> getErrorList();

}
