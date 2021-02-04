package src.shopping.inter;

import src.inter.IServiceLocator;

public interface IStockManager {
	
	public IServiceLocator getServiceLocator();
	
	public void consumeStock(Integer oferta_id, Integer unidades);
	
	public void recuperaStock(Integer oferta_id, Integer unidades);
	
	public Integer disponible(Integer oferta_id);

}
