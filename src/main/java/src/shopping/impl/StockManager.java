package src.shopping.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Oferta;
import src.inter.IServiceLocator;
import src.shopping.inter.IStockManager;

@RequestScoped
public class StockManager implements IStockManager {
	
	@Inject
	private IServiceLocator serviceLocator;

	private Oferta oferta;


	@Override
	public Boolean consumirStock(Integer oferta_id, Integer unidades) {
		Boolean ok = true;
		// check availability
		int nuevoStock = disponible(oferta_id) - unidades;
		if(nuevoStock >= 0){
			updateStock(oferta, nuevoStock);		
			
		}else{
			ok = false;
//			throw new RuntimeException("Stock Exception : oferta_id = " + oferta_id + ", unidades = " + unidades);
			
		}
		return ok;
	}

	@Override
	public Boolean recuperarStock(Integer oferta_id, Integer unidades) {
		Boolean ok = true;
		// TODO Auto-generated method stub
		
		return ok;
	}

	@Override
	public Integer disponible(Integer oferta_id) {
		Integer result = 0;
		load(oferta_id);
		if(oferta != null)result = oferta.getStock();

		return result;
	}

	@Override
	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	@Override
	public Integer disponible() {
		Integer result = 0;
		if(oferta != null)result = oferta.getStock();

		return result;

	}

	@Override
	public void load(Integer oferta_id) {
		oferta = serviceLocator.getOfertaServices().read(oferta_id);		
	}

	@Override
	public Oferta updateStock(Oferta oferta, Integer unidades) {
		oferta.setStock(unidades);
		oferta = serviceLocator.getOfertaServices().update(oferta);
		return oferta;
	}

}
