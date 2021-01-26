package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.inter.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class CartValuedBB implements IProcessable{

	@Inject 
	private IShoppingFacade shoppingFacade;	
	
	
	
	public String moreItems(){
		return shoppingFacade.getServiceLocator().getViewStateMachine().setAvailabilityView();
	}
	
	public void removeItem(CartItem item){
		shoppingFacade.removeItemFromCart(item);
	}
	
	public void reset(){
		shoppingFacade.resetCart();
	}
	
	public String configOrder(){
		return shoppingFacade.configOrder();
	}
	
	
//	public String createOrder(){
//		return shoppingFacade.createOrder();
//	}

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
		// elimina la oferta del carrito
		int indice = 0;
		if(obj instanceof Integer){
			indice = (Integer)obj;
		}
		if(obj instanceof String){
			indice = Integer.valueOf((String)obj);
		}
		for(CartItem of : shoppingFacade.getCart().getListaItems()){
			if(of.getId().intValue() == indice){
				removeItem(of);
			}			
		}		
		return null;
	}
	
	
	public void modifItem(Integer id, Integer numItems){
		System.out.println("CartValuedBB.modifItem - itemid = " + id + ", nuevo numItems = " + numItems);
	}
	

	
	

}
