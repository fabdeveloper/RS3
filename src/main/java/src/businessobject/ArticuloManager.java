package src.businessobject;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.inter.IServiceLocator;

@SessionScoped
public class ArticuloManager implements IArticuloManager, Serializable {

	@Inject 
	private IServiceLocator serviceLocator;
	
	

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	@Override
	public List<Articulo> getArticulosList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Articulo> getArticulosByProductId(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articulo getArticuloByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articulo getArticuloById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
