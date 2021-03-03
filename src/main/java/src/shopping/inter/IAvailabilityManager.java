package src.shopping.inter;

import java.util.List;

import src.businessobject.IProductManager;
import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;

public interface IAvailabilityManager extends IProductManager{
	
	public List<Product> getAvail();	
	public  List<Articulo> getAvail(Product prod);	
	public List<Oferta> getAvail(Articulo articulo);

}
