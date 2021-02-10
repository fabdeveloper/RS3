package src.shopping.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Oferta;
import src.exception.ConsumeStockException;
import src.exception.DBException;
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
		// consume stock
		if(nuevoStock >= 0){
			try{
				updateStock(oferta, nuevoStock);		

			}catch(Throwable t){
				throw new DBException("error consumiendo stock", t);
			}
			
		}else{
			ok = false;
			throw new ConsumeStockException("insufficient stock available").setOferta(oferta).setDisponible(oferta.getStock());			
		}
		return ok;
	}

	@Override
	public Boolean recuperarStock(Integer oferta_id, Integer unidades) {
		Boolean ok = true;
		int nuevoStock = disponible(oferta_id) + unidades;
		try{
			updateStock(oferta, nuevoStock);		

		}catch(Throwable t){
			throw new DBException("error recuperando stock", t);
		}
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
		Oferta ofertaActualizada = oferta.clone();
		ofertaActualizada.setStock(unidades);		
		oferta = serviceLocator.getOfertaServices().update(ofertaActualizada);
		return oferta;
	}

	@Override
	public Oferta getOferta() {
		return oferta;
	}

}
