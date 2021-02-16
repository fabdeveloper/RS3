package src.shopping.impl;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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

}
