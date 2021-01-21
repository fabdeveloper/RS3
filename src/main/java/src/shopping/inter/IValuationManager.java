package src.shopping.inter;

import java.util.List;

import src.entity.CartItem;
import src.entity.Oferta;
import src.entity.User;

public interface IValuationManager {
	
	public Float valuate();
	public void setListaItems(List<CartItem> listaItems);
//	public void setDiscounts(List<Discount> discounts);
//	public void setUser(User user);
//	public void  setTaxes(List<Taxe> taxes);

}
