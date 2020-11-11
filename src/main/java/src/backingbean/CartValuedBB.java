package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Cart;
import src.entity.Oferta;
import src.inter.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class CartValuedBB implements IProcessable{

	@Inject 
	private IShoppingFacade shoppingFacade;	
	
	
	
	public String moreItems(){
		return "availview";
	}
	
	public void removeItem(Oferta oferta){
		shoppingFacade.removeItemFromCart(oferta);
	}
	
	public void reset(){
		shoppingFacade.resetCart();
	}
	
	public void createOrder(){
		shoppingFacade.createOrder();
	}

	public Cart getCart() {
		return shoppingFacade.getCart();
	}


	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}


	@Override
	public String process(Object obj) {
		int indice = 0;
		if(obj instanceof Integer){
			indice = (Integer)obj;
		}
		if(obj instanceof String){
			indice = Integer.valueOf((String)obj);
		}
		for(Oferta of : shoppingFacade.getCart().getListaOfertas()){
			if(of.getId().intValue() == indice){
				removeItem(of);
			}			
		}		
		return null;
	}
	
	

	
	

}
