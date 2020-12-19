package src.shopping.impl;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import src.shopping.inter.IViewStateMachine;


@RequestScoped
public class ViewStateMachine implements IViewStateMachine {

	@Override
	public String setAvailabilityView() {
		return "availabilityview";
	}

	@Override
	public String setOfertaView() {
		return "ofertaview";
	}

	@Override
	public String setCartView() {
		return "cartview";
	}

	@Override
	public String setOrderView() {
		return "orderview";
	}

	@Override
	public String setConfigView() {
		return "configview";
	}

}
