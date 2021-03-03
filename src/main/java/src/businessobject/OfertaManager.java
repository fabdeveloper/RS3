package src.businessobject;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Oferta;
import src.inter.IServiceLocator;

@SessionScoped
public class OfertaManager implements IOfertaManager, Serializable {


	@Inject 
	private IServiceLocator serviceLocator;
	
	

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	@Override
	public List<Oferta> getOfertasList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Oferta> getOfertasByProductId(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Oferta> getOfertasByArticuloId(Integer articulo_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Oferta getOfertaById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Oferta getOfertaByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
