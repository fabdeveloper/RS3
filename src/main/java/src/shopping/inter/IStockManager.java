package src.shopping.inter;

import src.entity.Oferta;
import src.inter.IServiceLocator;

public interface IStockManager {
	
	public IServiceLocator getServiceLocator();
	
	public Boolean consumirStock(Integer oferta_id, Integer unidades);
	
	public Boolean recuperarStock(Integer oferta_id, Integer unidades);
	
	public Integer disponible(Integer oferta_id);
	
	public Integer disponible();
	
	public void load(Integer oferta_id);
	
	public Oferta updateStock(Oferta oferta, Integer unidades);
	
	public Oferta getOferta();

}
