package src.shopping.impl;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import src.inter.IServiceLocator;
import src.shopping.inter.ISessionManager;

public class SessionManager implements ISessionManager {
	
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
		return serviceLocator.getSessionContext().isCallerInRole("CLIENT");
	}
	
	@Override
	public String getCallerName(){		
		return serviceLocator.getSessionContext().getCallerPrincipal().getName();		
	}

}
