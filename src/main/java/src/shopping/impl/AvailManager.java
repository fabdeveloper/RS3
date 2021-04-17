package src.shopping.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import src.businessobject.IArticuloManager;
import src.businessobject.IOfertaManager;
import src.businessobject.IProductManager;
import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.inter.IServiceLocator;
import src.querystrategy.AbstractQueryStrategy;
import src.querystrategy.IQueryStrategyManager;
import src.querystrategy.OfertasPorArticuloName;
import src.querystrategy.TodasLasOfertasQS;
import src.shopping.inter.IAvailabilityManager;
import src.transferobject.OfertaViewTO;

@SessionScoped
public class AvailManager implements IAvailabilityManager, Serializable {
	
	@Inject 
	private IServiceLocator serviceLocator;
	@Inject
	private IProductManager productManager;
	@Inject 
	private IArticuloManager articuloManager;
	@Inject 
	private IOfertaManager ofertaManager;
	
	@Inject
	private IQueryStrategyManager<Oferta, OfertaViewTO> queryManager;
	

	@Override
	public List<Product> getAvail() {
		System.out.println("AvailManager - cargando producto ...");
		return productManager.getAll();
	}

	@Override
	public List<Articulo> getAvail(Product prod) {
		return articuloManager.getArticulosByProductId(prod.getId());
	}

	@Override
	public List<Oferta> getAvail(Articulo articulo) {
		return ofertaManager.getOfertasByArticuloId(articulo.getId());
	}

	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	public IArticuloManager getArticuloManager() {
		return articuloManager;
	}

	public void setArticuloManager(IArticuloManager articuloManager) {
		this.articuloManager = articuloManager;
	}

	public IOfertaManager getOfertaManager() {
		return ofertaManager;
	}

	public void setOfertaManager(IOfertaManager ofertaManager) {
		this.ofertaManager = ofertaManager;
	}

	@Override
	public List<Articulo> getArticulosByProductName(String prod_name) {		
		Product prod = productManager.getByName(prod_name);
		productManager.setSelected(prod);
		List<Articulo> list = null;
		if(prod != null) list = getAvail(prod);
		return list;
	}

	@Override
	public List<Oferta> getOfertasByArticuloName(String arti_name) {
		Articulo arti = articuloManager.getByName(arti_name);
		articuloManager.setSelected(arti);
		List<Oferta> list = null;
		if(arti != null) list = getAvail(arti);
		return list;
	}

	@Override
	public IQueryStrategyManager getQueryManager() {
		if(queryManager.getStrategy() == null) {
			TodasLasOfertasQS defaultStrategy = new TodasLasOfertasQS();
			defaultStrategy.setServiceLocator(getServiceLocator());
			queryManager.setStrategy(defaultStrategy);
		}
		return queryManager;
	}



	@Override
	public void searchName(String name) {
		
		AbstractQueryStrategy ofertasPorTexto = new AbstractQueryStrategy() {
			@Override
			public List<Oferta> executeStrategy() {
				return getServiceLocator().getOfertaServices().createNamedQueryListResult("ofertasByString", "some_text", name);
			}			
		};

		ofertasPorTexto.setParametro(name);
		ofertasPorTexto.setServiceLocator(getServiceLocator());
		this.getQueryManager().setStrategy(ofertasPorTexto);
		this.getQueryManager().refresh();
		
		
		
//		AbstractQueryStrategy ofertasPorArticuloNameQuery = new OfertasPorArticuloName();
//		ofertasPorArticuloNameQuery.setParametro(name);
//		ofertasPorArticuloNameQuery.setServiceLocator(getServiceLocator());
//		this.getQueryManager().setStrategy(ofertasPorArticuloNameQuery);
//		this.getQueryManager().refresh();
		
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public void setQueryManager(IQueryStrategyManager<Oferta, OfertaViewTO> queryManager) {
		this.queryManager = queryManager;
	}

	
}
