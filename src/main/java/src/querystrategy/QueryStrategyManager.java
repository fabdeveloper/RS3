/**
 * 
 */
package src.querystrategy;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import src.entity.Oferta;

/**
 * @author fabo_
 *
 */

@SessionScoped
public class QueryStrategyManager extends AbstractQueryStrategyManager implements Serializable {
	
	private IQueryStrategy strategy;

	@Override
	public void updateList() {		
		listaResultados = getStrategy().executeStrategy();
	}

	public IQueryStrategy getStrategy() {
//		if(strategy == null) {
//			UltimasOfertasQS ultimas = new UltimasOfertasQS();
//			ultimas.setServiceLocator(null);
//			setStrategy(ultimas);
//		}
		return strategy;
	}

	@Override
	public void setStrategy(IQueryStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 
	 */
	public QueryStrategyManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param strategy
	 */
	public QueryStrategyManager(IQueryStrategy strategy) {
		super();
		this.strategy = strategy;
	}
	
	

}
