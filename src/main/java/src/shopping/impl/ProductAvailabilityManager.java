package src.shopping.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.inter.IServiceLocator;
import src.shopping.inter.IAvailabilityManager;

//@RequestScoped
//@Alternative
public class ProductAvailabilityManager /*implements IAvailabilityManager*/ {
	
//	@Inject
//	private IServiceLocator serviceLocator;
//
//
//	@Override
//	public List<Product> getAvail(){
//		return serviceLocator.getProductServices().readAll();
//	}
//	@Override
//	public  List<Articulo> getAvail(Product prod){
//		return serviceLocator.getArticuloServices().getGestorE()
//				.getDao().createNamedQueryListResultIntParam("articulos por product_id", "product_id", prod.getId());
//	}
//	@Override
//	public List<Oferta> getAvail(Articulo articulo){
//		return serviceLocator.getOfertaServices().getGestorE()
//				.getDao().createNamedQueryListResultIntParam("ofertas por articulo_id", "articulo_id", articulo.getId());
//	}
//	@Override
//	public List<Articulo> getArticulosByProductName(String prod_name) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
