package src.businessobject;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entitymanager.AbstractEntityManager;
import src.inter.IServiceLocator;

@SessionScoped
public class ArticuloManager extends AbstractEntityManager<Articulo> implements IArticuloManager, Serializable {



	private static final long serialVersionUID = 10001L;

	@Override
	public List<Articulo> getArticulosByProductId(Integer prod_id) {
		setAll(getEntityServices().createNamedQueryListResultIntParam("articulosByProductId", "product_id", prod_id));
		return getAll();
	}

	@Override
	public Articulo getByName(String name) {
		Articulo sel = null;
		for(Articulo art : getAll()){
			if(art.getName().matches(name)){
				sel = art;
				break;
			}
		}
		return sel;
	}

	@Override
	public Articulo getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
