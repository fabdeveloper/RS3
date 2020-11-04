package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class PurchaseViewBB {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	
	public void purchaseConfirm(){
		shoppingFacade.purchaseConfirm();		
	}

}
