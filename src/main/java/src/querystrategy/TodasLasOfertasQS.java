/**
 * 
 */
package src.querystrategy;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import src.entity.Oferta;
import src.inter.IServiceLocator;

/**
 * @author fabo_
 *
 */
public class TodasLasOfertasQS implements IQueryStrategy<Oferta> {
	
	private IServiceLocator serviceLocator;

	@Override
	public List<Oferta> executeStrategy() {
		String msg = "Numero de ofertas = " + serviceLocator.getOfertaServices().readAll().size();		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		
	
		return serviceLocator.getOfertaServices().readAll();
	}
	
	
	
	
	
	
	
	
	/**********************************/

	
	

	/**
	 * @param serviceLocator
	 */
	public TodasLasOfertasQS(IServiceLocator serviceLocator) {
		super();
		this.serviceLocator = serviceLocator;
	}


	/**
	 * 
	 */
	public TodasLasOfertasQS() {
		super();
		// TODO Auto-generated constructor stub
	}




	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	
	
	
	
	

}
