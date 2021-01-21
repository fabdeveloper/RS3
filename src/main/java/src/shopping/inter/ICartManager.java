package src.shopping.inter;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;

public interface ICartManager {
	
	public Cart addItem(Oferta item, Integer numItems);
	public Cart removeItem(CartItem item);
	public Cart reset();
	public Cart getCart();

}
