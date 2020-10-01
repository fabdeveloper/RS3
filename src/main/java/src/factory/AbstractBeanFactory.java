package src.factory;

import javax.inject.Inject;

import src.inter.Prototype;

public abstract class AbstractBeanFactory<P> implements IBeanFactory<P> {
	
	@Inject
	private Prototype<P> bean;	

	@Override
	public P crear() {
		return bean.clone();
	}

}
