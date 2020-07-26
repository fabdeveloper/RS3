package src.wizzard;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


import src.dao.AbstractDao;
import src.dao.PedidoDao;
import src.entity.Pedido;
import src.factory.IFactory;
import src.factory.FactoryImpl;
import src.factory.PedidoFactory;
import src.factory.BeanFactory;
import src.inter.IGestorE;


@RequestScoped
public class PedidoGestor implements IGestorE<Pedido>{
	
	@Inject private PedidoFactory factory;
	@Inject private PedidoDao dao;
	
	
	
	
	@Override
	public BeanFactory<Pedido> getFactory() {
		return factory;
	}
	@Override
	public AbstractDao<Pedido> getDao() {
		return dao;
	}
	public void setFactory(PedidoFactory factory) {
		this.factory = factory;
	}
	public void setDao(PedidoDao dao) {
		this.dao = dao;
	}
	
	



}
