package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.User;
import src.shopping.inter.IShoppingFacade;

@Named
@RequestScoped
public class UserManagementBB {
	
	@Inject 
	private IShoppingFacade shoppingFacade;	

	private User user = null;
	private boolean loaded = false;
	
	
	public void load(){	
//		System.out.println("UserManagementBB.load - loading user ... - " + new Date());
		if(shoppingFacade.isClient()){
			user = shoppingFacade.getServiceLocator().getUserServices().createNamedQuery("byNick", "nick", shoppingFacade.getCallerName());
		}else{
			user = shoppingFacade.getServiceLocator().getUserServices().getTransferObject();
		}
	}
	
	@Transactional
	public void createNew(){
		user.addGrupo(
				shoppingFacade.getServiceLocator().getGrupoServices()
					.createNamedQuery("grupo_byName", "name", "CLIENTS"));
		
		shoppingFacade.getServiceLocator().getUserServices().persist(user);
	}
	
	@Transactional
	public void updateUser(){
		shoppingFacade.getServiceLocator().getUserServices().merge(user);
	}
	
	public String setUserManagementView(){
		return shoppingFacade.getViewStateMachine().setUserManagementView();
	}
	
	public String setRestorePasswordView(){
		return null;
	}

	public User getUser() {
		if(user == null){
			load();
		}
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

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	

}
