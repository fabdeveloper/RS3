package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class HomeBarBB {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	public String getHomeButtonText(){
		return shoppingFacade.getString("searchbar_homebuttontext");
	}
	
	public String homeButtonAction(){
		return shoppingFacade.getViewStateMachine().setHomeView();		
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}
	
	
	



}
