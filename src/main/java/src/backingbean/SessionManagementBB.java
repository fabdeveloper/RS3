package src.backingbean;

import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.inter.IProcessable;
import src.shopping.inter.IShoppingFacade;

@Named
@SessionScoped
public class SessionManagementBB implements Serializable, IProcessable {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private boolean isLoaded = false;
	
	private String user;
	private String password;
	
	
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
	
	public boolean isLoaded(){
		return loadPendingOrder();
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String process(){
		login();
		return null;		
	}
	
	public String login(){
		shoppingFacade.login(user, password);
		return null;
	}
	
	public String logout(){
		shoppingFacade.logout();
		return null;
	}


}
