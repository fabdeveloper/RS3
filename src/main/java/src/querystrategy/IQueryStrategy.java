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
public interface IQueryStrategy<E> {
	
	public List<E> executeStrategy();
	
	public IServiceLocator getServiceLocator();
	public void setServiceLocator(IServiceLocator serviceLocator);


}
