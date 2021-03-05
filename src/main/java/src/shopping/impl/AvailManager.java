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
import src.shopping.inter.IAvailabilityManager;

@SessionScoped
public class AvailManager implements IAvailabilityManager, Serializable {
	
	@Inject
	private IProductManager productManager;
	@Inject 
	private IArticuloManager articuloManager;
	@Inject 
	private IOfertaManager ofertaManager;
	

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

	
}
