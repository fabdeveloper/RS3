package src.businessobject;

import java.util.List;

import src.entity.Oferta;
import src.entitymanager.IEntityManager;

public interface IOfertaManager extends IEntityManager<Oferta> {
	
	public List<Oferta> getOfertasByProductId(Integer prod_id);
	public List<Oferta> getOfertasByArticuloId(Integer articulo_id);

	

}
