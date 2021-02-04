package src.shopping.impl;

import javax.inject.Inject;

import src.inter.IServiceLocator;
import src.shopping.inter.IStockManager;

public class StockManager implements IStockManager {
	
	@Inject
	private IServiceLocator serviceLocator;



	@Override
	public void consumeStock(Integer oferta_id, Integer unidades) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recuperaStock(Integer oferta_id, Integer unidades) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer disponible(Integer oferta_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

}
