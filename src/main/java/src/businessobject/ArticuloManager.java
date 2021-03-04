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



	@Override
	public List<Articulo> getArticulosByProductId(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articulo getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articulo getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
