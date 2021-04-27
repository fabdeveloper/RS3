/**
 * 
 */
package src.querystrategy;

import java.util.ArrayList;
import java.util.List;

import src.entity.Oferta;
import src.transferobject.OfertaResultViewTO;
import src.transferobject.OfertaViewTO;


public abstract class AbstractQueryStrategyManager<E> implements IQueryStrategyManager<E> {
	
	protected List<E> list;
	protected IQueryStrategy<E> strategy;

	@Override
	public List<E> getList() {
		if(list == null) {
			loadList();
		}
		return list;
	}


	@Override
	public IQueryStrategy<E> getStrategy() {
		return strategy;
	}

	@Override
	public void reset() {
		list = null;
	}



	@Override
	public void setStrategy(IQueryStrategy<E> strategy) {
		this.strategy = strategy;
		reset();
	}
	
	@Override
	public void loadList() {
		list = getStrategy().executeStrategy();
	}
	
	

	
	

}
