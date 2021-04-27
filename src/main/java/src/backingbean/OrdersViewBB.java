/**
 * 
 */
package src.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Order;
import src.inter.IServiceLocator;
import src.querystrategy.AllOrdersByNick;
import src.querystrategy.IQueryStrategy;
import src.querystrategy.IQueryStrategyManager;
import src.querystrategy.orders.OrderQueryStrategyManager;

@Named
@SessionScoped
public class OrdersViewBB implements Serializable {
	
	private String itemSel;
	
	private IQueryStrategyManager<Order> qsm;
	
	@Inject
	private IServiceLocator serviceLocator;


	public IQueryStrategyManager<Order> getQsm() {
		if(qsm == null) {
			initQsm();
		}
		return qsm;
	}
	
	public void initQsm() {
		IQueryStrategy<Order> strategy = new AllOrdersByNick();
		strategy.setServiceLocator(getServiceLocator());
		qsm = new OrderQueryStrategyManager();
		qsm.setStrategy(strategy);
		qsm.reset();
		
	}
	
	

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public void setQsm(IQueryStrategyManager<Order> qsm) {
		this.qsm = qsm;
	}

	public String getItemSel() {
		return itemSel;
	}

	public void setItemSel(String itemSel) {
		this.itemSel = itemSel;
	}

	public List<Order> getList() {
		return getQsm().getList();
	}

//	public void setList(List<Order> list) {
//		this.list = list;
//	}

}
