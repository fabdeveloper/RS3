/**
 * 
 */
package src.querystrategy;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

/**
 * @author fabo_
 *
 */

@SessionScoped
public class QueryStrategyManager extends AbstractQueryStrategyManager implements Serializable {
	
	private IQueryStrategy strategy;

	@Override
	public void updateList() {		
		listaResultados = strategy.executeStrategy();
	}

	public IQueryStrategy getStrategy() {
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
