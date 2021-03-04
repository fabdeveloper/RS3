package src.businessobject;

import java.util.List;

import src.entity.Articulo;
import src.entitymanager.IEntityManager;

public interface IArticuloManager extends IEntityManager<Articulo> {
	
	public List<Articulo> getArticulosByProductId(Integer prod_id);


	

}
