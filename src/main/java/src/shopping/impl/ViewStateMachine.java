package src.shopping.impl;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import src.shopping.inter.IViewStateMachine;


@RequestScoped
public class ViewStateMachine implements IViewStateMachine {

	@Override
	public String setAvailabilityView() {
		return "availability";
	}

	@Override
	public String setOfertaView() {
		return "oferta";
	}

	@Override
	public String setCartView() {
		return "cart";
	}

	@Override
	public String setOrderView() {
		return "order";
	}
	@Override
	public String setOrdersView() {
		return "orders";
	}

	@Override
	public String setConfigView() {
		return "config";
	}

	@Override
	public String setHomeView() {
		return "home";
	}
	
	@Override
	public String setErrorView(){
		return "error";
	}

	@Override
	public String setLoginView() {
		return "login";
	}

	@Override
	public String setUserManagementView() {
		return "user";
	}



}
