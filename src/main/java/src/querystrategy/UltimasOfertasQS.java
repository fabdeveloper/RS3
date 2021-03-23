/**
 * 
 */
package src.querystrategy;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Oferta;
import src.inter.IServiceLocator;

/**
 * @author fabo_
 *
 */


public class UltimasOfertasQS implements IQueryStrategy {

	private IServiceLocator serviceLocator;
	
	
	
	
	
	/**
	 * @param serviceLocator
	 */
	public UltimasOfertasQS(IServiceLocator serviceLocator) {
		super();
		this.serviceLocator = serviceLocator;
	}


	/**
	 * 
	 */
	public UltimasOfertasQS() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<Oferta> executeStrategy() {
		return ultimasOfertasAvail();
	}
	
	
	public List<Oferta> ultimasOfertasAvail() {		
		if(serviceLocator == null) System.out.println("SERViceLocator es NULL");
		serviceLocator.getShoppingFacade().publish("service locator = " + serviceLocator);
		return serviceLocator.getOfertaServices().createNamedQueryLimited("ofertasAll", 20);		 
	}


	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}


	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	

}