/**
 * 
 */
package src.querystrategy;

import java.util.List;

import src.entity.Oferta;
import src.transferobject.OfertaResultViewTO;
import src.transferobject.OfertaViewTO;

/**
 * @author fabo_
 *
 */


public interface IQueryStrategyManager<LISTARE, LISTATO> {
	
	public List<LISTARE> getListaResultados();
	public List<LISTATO> getListaTO();
	public void updateList();
	/**
	 * @return
	 */
	public List<OfertaResultViewTO> getListResultTO();
	
	public void setStrategy(IQueryStrategy strategy);
	
	public void refresh();

}
