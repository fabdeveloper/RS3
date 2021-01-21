package src.factory;

import javax.inject.Inject;

import src.inter.IPrototype;

public abstract class AbstractBeanFactory<P> implements IBeanFactory<P> {
	
	@Inject
	private IPrototype<P> bean;	

	@Override
	public P crear() {
		return bean.clone();
	}

}
