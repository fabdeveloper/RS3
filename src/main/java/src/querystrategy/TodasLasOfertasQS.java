/**
 * 
 */
package src.querystrategy;

import java.util.List;

import src.entity.Oferta;
import src.inter.IServiceLocator;

/**
 * @author fabo_
 *
 */
public class TodasLasOfertasQS implements IQueryStrategy {
	
	private IServiceLocator serviceLocator;

	@Override
	public List<Oferta> executeStrategy() {
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
