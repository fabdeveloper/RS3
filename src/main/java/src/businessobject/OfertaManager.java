package src.businessobject;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Oferta;
import src.entitymanager.AbstractEntityManager;
import src.inter.IServiceLocator;

@SessionScoped
public class OfertaManager extends AbstractEntityManager<Oferta> implements IOfertaManager, Serializable {


	@Override
	public List<Oferta> getOfertasByProductId(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Oferta> getOfertasByArticuloId(Integer articulo_id) {
		setAll(getEntityServices().createNamedQueryListResultIntParam("ofertasByArticuloId", "articulo_id", articulo_id));
		return getAll();	
	}

	@Override
	public Oferta getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Oferta getByName(String name) {
		Oferta sel = null;
		for(Oferta of : getAll()){
			if(of.getName().matches(name)){
				sel = of;
				break;
			}
		}
		return sel;
	}

}
