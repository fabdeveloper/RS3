package src.backingbean;

import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.shopping.inter.IShoppingFacade;

@Named
@SessionScoped
public class SessionManagementBB implements Serializable {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private boolean isLoaded = false;
	
	
	public Boolean loadPendingOrder(){
		if(!isLoaded){
			isLoaded = shoppingFacade.loadPendingOrder();
		}
		return isLoaded;
	}
	
	public String getCallerName(){
		return shoppingFacade.getCallerName();
	}
	
	public String invalidateSession(){
//		String response = shoppingFacade.invalidateSession();
//		isLoaded = false;
//		return response;
		
		return shoppingFacade.invalidateSession();
	}
	
	public Boolean isClient(){
		return shoppingFacade.isClient();
	}
	
	public boolean getIsLoaded() {
		return loadPendingOrder();
	}
	
	public void setIsLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public boolean isLoaded() {
		return loadPendingOrder();
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

}
