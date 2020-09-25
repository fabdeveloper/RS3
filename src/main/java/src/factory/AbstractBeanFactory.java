package src.factory;

import javax.inject.Inject;

public abstract class AbstractBeanFactory<P> implements IBeanFactory<P> {
	
	@Inject
	private P bean;	

	@Override
	public P crear() {
		return bean;
	}

}
