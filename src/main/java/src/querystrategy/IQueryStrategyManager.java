/**
 * 
 */
package src.querystrategy;

import java.util.List;

import src.entity.Oferta;
import src.transferobject.OfertaViewTO;

/**
 * @author fabo_
 *
 */


public interface IQueryStrategyManager<LISTARE, LISTATO> {
	
	public List<LISTARE> getListaResultados();
	public List<LISTATO> getListaTO();
	public void updateList();

}
