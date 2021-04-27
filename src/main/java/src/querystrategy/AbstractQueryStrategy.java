/**
 * 
 */
package src.querystrategy;

import src.entity.Oferta;
import src.inter.IServiceLocator;

/**
 * @author fabo_
 *
 */
public abstract class AbstractQueryStrategy<E> implements IQueryStrategy<E> {
	
	protected String parametro;
	
	protected IServiceLocator serviceLocator;

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}




}
