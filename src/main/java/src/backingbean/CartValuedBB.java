package src.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Cart;
import src.entity.CartItem;
import src.entity.Oferta;
import src.util.interfaces.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class CartValuedBB implements IProcessable{

	@Inject 
	private IShoppingFacade shoppingFacade;	
	
	
	
	public String moreItems(){
		return shoppingFacade.getViewStateMachine().setAvailabilityView();
	}
	
	public void removeItem(CartItem item){
		shoppingFacade.removeItemFromCart(item);
	}
	
	@Transactional
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
	
	public String showCart(){
		return shoppingFacade.showCart();
	}


	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}


	@Transactional
	@Override
	public String process(Object obj) {
		System.out.println("CartValuedBB.process() - ");
		// elimina la oferta del carrito
		int indice = 0;
		if(obj instanceof Integer){
			indice = (Integer)obj;
		}
		if(obj instanceof String){
			indice = Integer.valueOf((String)obj);
		}
		for(CartItem of : shoppingFacade.getCart().getListaItems()){
			System.out.println("indice a eliminar = " + indice);
			System.out.println("CartItem:  " + of);
		}
		CartItem itemNoDeseado = null;
		for(CartItem of : shoppingFacade.getCart().getListaItems()){			
			if(of.getId().intValue() == indice){
				itemNoDeseado = of;				
			}			
		}
		if(itemNoDeseado != null)removeItem(itemNoDeseado);
		
		return null;
	}
	
	@Override
	public String process(){
		return configOrder();
	}
	
	@Transactional
	public String modifItem(Integer id, Integer numItems){
		System.out.println("CartValuedBB.modifItem - itemid = " + id + ", nuevo numItems = " + numItems);
		return shoppingFacade.changeNumItems(id, numItems);
	}
	
	public List<SelectItem> getListaStock(Integer stock){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (int i = 1; i <= stock; i++) {
			String num = "" + i;
			lista.add(new SelectItem(i, num));
		}
		return lista;		
	}
	

	
	

}
