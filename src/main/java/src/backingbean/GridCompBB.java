/**
 * 
 */
package src.backingbean;

import java.util.ArrayList;
import java.util.Iterator;
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
import src.transferobject.OfertaResultViewTO;
import src.transferobject.OfertaViewTO;

@Named
@RequestScoped
public class GridCompBB implements IProcessable, IGridMaker {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private IQueryStrategyManager<Oferta,OfertaViewTO> querysm;
	
	private Integer cols = 4;
	
	private List<OfertaViewTO> list;
	
	private List<OfertaResultViewTO> listResult;
	
	private int counter = 0;

	private List<List<OfertaViewTO>> matriz;
	
	private List<List<OfertaResultViewTO>> matrizResult;
	
	private Iterator iterator;
	
	

	
	public void initMatriz() {
		List<List<OfertaViewTO>> matrizTemp = new ArrayList<List<OfertaViewTO>>();
//		int end = 0;
		for(int start = 0, end = start+cols; 				
				start < getList().size() && end <= getList().size(); 				
				start= end+1 , 
						end = (start+cols > getList().size()) ? getList().size() : start+cols)			
		{
			matrizTemp.add(getList().subList(start, end));
		}
		matriz = matrizTemp;
	}
	
	public void initMatrizResult() {
		List<List<OfertaResultViewTO>> matrizTemp = new ArrayList<List<OfertaResultViewTO>>();
		for(int start = 0, end = start+cols; 				
				start < getList().size() && end <= getList().size(); 				
				start= end+1 , 
						end = (start+cols > getList().size()) ? getList().size() : start+cols)			
		{
			matrizTemp.add(getListResult().subList(start, end));
		}
		matrizResult = matrizTemp;
	}
	
	public List<List<OfertaViewTO>> getMatriz() {
		if(matriz == null) {
			initMatriz();
		}
		return matriz;
	}

	public void setMatriz(List<List<OfertaViewTO>> matriz) {
		this.matriz = matriz;
	}


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
		list = getQuerysm().getListaTO();
	}
	
	public void initListResult() {
		listResult = getQuerysm().getListResultTO();
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

	public IQueryStrategyManager<Oferta, OfertaViewTO> getQuerysm() {
		if(querysm == null) {
			querysm = getShoppingFacade().getAvailabilityManager().getQueryManager();			
		}
		return querysm;
	}

	public void setQuerysm(IQueryStrategyManager<Oferta, OfertaViewTO> querysm) {
		this.querysm = querysm;
	}

	public Iterator getIterator() {
		if(iterator == null) {
			iterator = getList().iterator();
		}
		return iterator;
	}

	public void setIterator(Iterator iterator) {
		this.iterator = iterator;
	}
	

	
	public List<OfertaResultViewTO> getListResult() {
		if(listResult == null) {
			initListResult();
		}
		return listResult;
	}

	public void setListResult(List<OfertaResultViewTO> listResult) {
		this.listResult = listResult;
	}

	public List<List<OfertaResultViewTO>> getMatrizResult() {
		if(matrizResult == null) {
			initMatrizResult();
		}
		return matrizResult;
	}

	public void setMatrizResult(List<List<OfertaResultViewTO>> matrizResult) {
		this.matrizResult = matrizResult;
	}



	
	

}
