package src.shopping.inter;

import src.entity.Cart;
import src.entity.Oferta;

public interface ICartManager {
	
	public Cart addItem(Oferta item);
	public Cart removeItem(Oferta item);
	public Cart reset();
	public Cart getCart();

}
