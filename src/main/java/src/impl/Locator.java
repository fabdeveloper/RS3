package src.impl;

import javax.inject.Inject;

import src.inter.ILocator;
import src.inter.IServiceLocator;

public class Locator implements ILocator {

	@Inject
	private IServiceLocator serviceLocator;
	
	
	@Override
	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}


	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	

}
