package src.shopping.impl;

import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
		System.out.println("caller name = " + getCallerName());
		System.out.println("is client ? = " + serviceLocator.getSessionContext().isCallerInRole("CLIENT"));
		System.out.println("is admin ? = " + serviceLocator.getSessionContext().isCallerInRole("ADMIN"));

		return (serviceLocator.getSessionContext().isCallerInRole("CLIENT")) || (serviceLocator.getSessionContext().isCallerInRole("ADMIN"));
	}
	
	@Override
	public String getCallerName(){		
		return serviceLocator.getSessionContext().getCallerPrincipal().getName();		
	}

}
