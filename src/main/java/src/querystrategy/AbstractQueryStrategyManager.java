/**
 * 
 */
package src.querystrategy;

import java.util.ArrayList;
import java.util.List;

import src.entity.Oferta;
import src.transferobject.OfertaViewTO;

/**
 * @author fabo_
 *
 */
public abstract class AbstractQueryStrategyManager implements IQueryStrategyManager<Oferta, OfertaViewTO> {
	
	protected List<Oferta> listaResultados;
	protected List<OfertaViewTO> listaTO;

	

	@Override
	public List<Oferta> getListaResultados() {
		if(listaResultados == null) {
			updateList();
		}
		return listaResultados;
	}

	@Override
	public List<OfertaViewTO> getListaTO() {
		if(listaTO == null) {
			listaTO = new ArrayList<OfertaViewTO>();
			for(Oferta oferta : getListaResultados()) {
				listaTO.add(OfertaViewTO.getNewOfertaViewTO(oferta));
			}			
		}		
		return listaTO;
	}

	@Override
	public abstract void updateList();

}
