/**
 * 
 */
package src.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Oferta;
import src.inter.IGridMaker;
import src.inter.IProcessable;
import src.querystrategy.IQueryStrategyManager;
import src.querystrategy.QueryStrategyManager;
import src.querystrategy.TodasLasOfertasQS;
import src.shopping.inter.IShoppingFacade;
import src.transferobject.OfertaViewTO;

@Named
@RequestScoped
public class GridCompBB implements IProcessable, IGridMaker {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private IQueryStrategyManager<Oferta,OfertaViewTO> querysm;
	
	private Integer cols = 4;
	
	private List<OfertaViewTO> list;
	private int counter = 0;

	@Override
	public void setCols(Integer cols) {
		this.cols = cols;
	}

	@Override
	public Integer getCols() {
		return cols;
	}

	@Override
	public List<OfertaViewTO> getList() {
		if(list == null) {
			initList();
		}
		return list;
	}
	
	public void initList() {
		list = querysm.getListaTO();
	}

	@Override
	public void setList(List<OfertaViewTO> list) {
		this.list = list;		
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void incrementCounter() {
		counter++;
	}
	
	public Integer counterMODcols() {		
		return counter % cols;
	}
	
	public Boolean isFirst() {
		return counterMODcols().intValue() == 0;
	}
	
	public Boolean isLast() {
		return counterMODcols().intValue() == (cols-1);
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	public IQueryStrategyManager getQuerysm() {
		if(querysm == null) {
			querysm = new QueryStrategyManager(new TodasLasOfertasQS(shoppingFacade.getServiceLocator()));
		}
		return querysm;
	}

	public void setQuerysm(IQueryStrategyManager querysm) {
		this.querysm = querysm;
	}


	
	

}