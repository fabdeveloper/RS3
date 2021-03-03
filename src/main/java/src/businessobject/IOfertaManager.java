package src.businessobject;

import java.util.List;

import src.entity.Oferta;

public interface IOfertaManager {
	
	public List<Oferta> getOfertasList();
	public List<Oferta> getOfertasByProductId(Integer prod_id);
	public List<Oferta> getOfertasByArticuloId(Integer articulo_id);
	public Oferta getOfertaById(Integer id);
	public Oferta getOfertaByName(String name);
	

}
