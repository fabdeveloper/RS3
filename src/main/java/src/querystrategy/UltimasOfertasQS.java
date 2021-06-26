/**
 * 
 */
package src.querystrategy;

import java.util.List;

import src.entity.Oferta;
import src.inter.IServiceLocator;


public class UltimasOfertasQS implements IQueryStrategy<Oferta> {

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
//		if(serviceLocator == null) System.out.println("SERViceLocator es NULL");
//		serviceLocator.getShoppingFacade().publish("service locator = " + serviceLocator);
		return serviceLocator.getOfertaServices().createNamedQueryLimited("ofertasAll", 20);		 
	}


	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}


	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	

}
