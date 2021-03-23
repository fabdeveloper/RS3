/**
 * 
 */
package src.querystrategy;

import java.util.List;

import src.entity.Oferta;

/**
 * @author fabo_
 *
 */
public interface IQueryStrategy {
	
	public List<Oferta> executeStrategy();

}
