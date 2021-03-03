package src.businessobject;

import java.util.List;

import src.entity.Articulo;

public interface IArticuloManager {
	
	public List<Articulo> getArticulosList();
	public List<Articulo> getArticulosByProductId(Integer prod_id);
	public Articulo getArticuloByName(String name);
	public Articulo getArticuloById(Integer id);
	

}
