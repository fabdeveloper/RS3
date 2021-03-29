/**
 * 
 */
package src.querystrategy;

import java.util.ArrayList;
import java.util.List;

import src.entity.Oferta;
import src.transferobject.OfertaResultViewTO;
import src.transferobject.OfertaViewTO;

/**
 * @author fabo_
 *
 */
public abstract class AbstractQueryStrategyManager implements IQueryStrategyManager<Oferta, OfertaViewTO> {
	
	protected List<Oferta> listaResultados;
	protected List<OfertaViewTO> listaTO;
	protected List<OfertaResultViewTO> listResultTO;

	
	
	
	
	@Override
	public void refresh() {
		
		listaResultados = null;
		listaTO = null;
		listResultTO = null;			
		
	}

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

	public List<OfertaResultViewTO> initListResultTO() {
		if(listResultTO == null) {
			listResultTO = new ArrayList<OfertaResultViewTO>();
			for(Oferta oferta : getListaResultados()) {
				listResultTO.add(OfertaResultViewTO.getOfertaResultViewTO(oferta));
			}			
		}		
		return listResultTO;
	}
	
	@Override
	public abstract void updateList();

	public List<OfertaResultViewTO> getListResultTO() {
		if(listResultTO == null) {
			initListResultTO();			
		}
		return listResultTO;
	}

	public void setListResultTO(List<OfertaResultViewTO> listResultTO) {
		this.listResultTO = listResultTO;
	}

	public void setListaResultados(List<Oferta> listaResultados) {
		this.listaResultados = listaResultados;
	}

	public void setListaTO(List<OfertaViewTO> listaTO) {
		this.listaTO = listaTO;
	}
	
	
	
	

}
