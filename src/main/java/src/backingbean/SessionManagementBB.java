package src.backingbean;

import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.shopping.inter.IShoppingFacade;

@Named
@SessionScoped
@DeclareRoles({"CLIENT","ADMIN"})
public class SessionManagementBB implements Serializable {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	public String getView(){
		boolean isClient = shoppingFacade.isClient();
		
//		boolean isClient = getCallerName().matches("fab");
		String respuesta = "";
		
		if(isClient){
			respuesta = "login/LogoutForm.xhtml";			
		}else{
			respuesta = "login/LoginForm.xhtml";	
		}
		
		return respuesta;	
	}
	
	public String getCallerName(){
		return shoppingFacade.getCallerName();
	}
	
	public String invalidateSession(){
		return shoppingFacade.invalidateSession();
	}

}
