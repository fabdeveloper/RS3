package src.gestor;

import javax.inject.Inject;

import src.dao.IDao;
import src.factory.IBeanFactory;

public abstract class AbstractGestor<T> implements IGestorE<T> {
	
	@Inject private IBeanFactory<T> factory;
	@Inject private IDao<T> dao;

	@Override
	public IBeanFactory<T> getFactory() {
		return factory;
	}

	@Override
	public IDao<T> getDao() {
		return dao;
	}

}
