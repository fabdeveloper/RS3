package src.shopping.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.inter.IServiceLocator;
import src.shopping.inter.IAvailabilityManager;

@RequestScoped
public class ProductAvailabilityManager implements IAvailabilityManager {
	
	@Inject
	private IServiceLocator serviceLocator;


	@Override
	public List<Product> getAvail(){
		return serviceLocator.getProductServices().getGestorE().getDao().getAll();
	}
	@Override
	public  List<Articulo> getAvail(Product prod){
		return serviceLocator.getArticuloServices().getGestorE()
				.getDao().createNamedQueryListResult("articulos por product_id", "product_id", prod.getId().toString());
	}
	@Override
	public List<Oferta> getAvail(Articulo articulo){
		return serviceLocator.getOfertaServices().getGestorE()
				.getDao().createNamedQueryListResult("ofertas por articulo_id", "articulo_id", articulo.getId().toString());
	}

}
