package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class LogoutBB {
	
	@Inject
	private IShoppingFacade shoppingFacade;


	

	
	public String pedidoButtonAction(){
		return getShoppingFacade().showOrders();
	}
	

	
	public String accountButtonAction(){
		return shoppingFacade.getViewStateMachine().setUserManagementView();
	}
	
	public String cartButtonText(){
		Integer numItems = shoppingFacade.getCartManager().getNumItems();
		String cart = shoppingFacade.getString("logout_cart");   // TODO : LOCALIZATION MANAGER
		String text = cart + ": " + numItems;
		return text;
	}
	
	public String cartButtonAction(){
		return shoppingFacade.getViewStateMachine().setCartView();
	}
	
	public String accountButtonText(){ // accountButtonText
		return shoppingFacade.getCallerName();
	}
	

	
	public String closeSessionAction(){		
		return shoppingFacade.logout();
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}
	
	
	
	
	/*****************************************/
	// LOCALIZATION
	
	
	/*
	public String closeSessionText(){
		return shoppingFacade.getString("logout_close_session_buttontext");
	}
	

	
	public String pedidoButtonText(){
		return shoppingFacade.getString("logout_pedido");
	}
	
	*/
	
	


	
	

}
