package src.gestor;

import javax.inject.Inject;

import src.dao.IDao;
import src.entity.Pedido;
import src.factory.BeanFactory;
import src.inter.IGestorE;

public abstract class AbstractGestor<T> implements IGestorE<T> {
	
	@Inject private BeanFactory<T> factory;
	@Inject private IDao<T> dao;

	@Override
	public BeanFactory<T> getFactory() {
		return factory;
	}

	@Override
	public IDao<T> getDao() {
		return dao;
	}

}
