/**
 * 
 */
package src.querystrategy;

/**
 * @author fabo_
 *
 */
public class QueryStrategyManager extends AbstractQueryStrategyManager {
	
	private IQueryStrategy strategy;

	@Override
	public void updateList() {		
		listaResultados = strategy.executeStrategy();
	}

	public IQueryStrategy getStrategy() {
		return strategy;
	}

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
