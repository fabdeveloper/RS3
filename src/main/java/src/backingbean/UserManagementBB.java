package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.User;
import src.shopping.inter.IShoppingFacade;

@Named
@RequestScoped
public class UserManagementBB {
	
	@Inject 
	private IShoppingFacade shoppingFacade;	

	private User user;
	
	
	public void load(){
		if(shoppingFacade.isClient()){
			user = shoppingFacade.getServiceLocator().getUserServices().createNamedQuery("byNick", "nick", shoppingFacade.getCallerName());
		}else{
			user = shoppingFacade.getServiceLocator().getUserServices().getTransferObject();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}
	
	

}
