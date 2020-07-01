package src.wizzard;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.beans.AbstractFacade;
import src.beans.PedidoFacade;
import src.dao.AbstractDao;
import src.dao.PedidoDao;
import src.entity.Pedido;
import src.factory.Factory;
import src.factory.FactoryImpl;
import src.factory.PedidoFactory;
import src.factory.ProductFactory;
import src.inter.Gestor;


@RequestScoped
public class PedidoGestor implements Gestor<Pedido>{
	
	@Inject private PedidoFactory factory;
	@Inject private PedidoDao dao;
	
	
	
	
	@Override
	public ProductFactory<Pedido> getFactory() {
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
