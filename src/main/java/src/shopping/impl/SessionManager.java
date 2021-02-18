package src.shopping.impl;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import src.exception.RS3LoginException;
import src.exception.URLException;
import src.inter.IServiceLocator;
import src.shopping.inter.ISessionManager;

@DeclareRoles({"CLIENT","ADMIN"})
@SessionScoped
public class SessionManager implements ISessionManager, Serializable {
	
	@Inject
	private IServiceLocator serviceLocator;

	@Override
	public void invalidateSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object session = externalContext.getSession(false);
		HttpSession httpSession = (HttpSession) session;
		httpSession.invalidate();
	}
	
	@Override
	public Boolean isClient(){	
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		return externalContext.isUserInRole("CLIENT");		
	}
	
	@Override
	public String getCallerName(){		
		return serviceLocator.getSessionContext().getCallerPrincipal().getName();		
	}

	@Override
	public void dispatch(String url) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		try {
			externalContext.dispatch(url);
		} catch (IOException e) {
			throw new URLException(url, e);
		}
		
	}

	@Override
	public void callLogin() {
//		String url = serviceLocator.getShoppingFacade().getViewStateMachine().setLoginView();
		String url = "/login/LoginForm.xhtml";
		dispatch(url);
	}

	@Override
	public Boolean login(String user, String password) {
		Boolean response = true;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try{
			request.login(user, password);
		}catch(Throwable t){
			response = false;
			throw new RS3LoginException("user = " + user, t);
		}
		return response;
	}

	@Override
	public Boolean logout() {
		Boolean response = true;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try{
			request.logout();
		}catch(Throwable t){
			response = false;
			throw new RS3LoginException("Logout error", t);
		}
		return response;
	}
	
	

}
