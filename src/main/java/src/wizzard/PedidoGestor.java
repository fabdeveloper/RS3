package src.wizzard;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;



import src.dao.AbstractDao;
import src.dao.PedidoDao;
import src.entity.Pedido;
import src.factory.BeanFactory;
import src.inter.IGestorE;


@RequestScoped
public class PedidoGestor implements IGestorE<Pedido>{
	
	@Inject private BeanFactory<Pedido> factory;
	@Inject private PedidoDao dao;

	
	
	
	
	@Override
	public BeanFactory<Pedido> getFactory() {
		return factory;
	}
	@Override
	public AbstractDao<Pedido> getDao() {
		return dao;
	}

	
	



}
