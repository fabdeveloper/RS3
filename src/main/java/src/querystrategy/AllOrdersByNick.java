package src.querystrategy;

import java.util.List;

import src.entity.Order;

public class AllOrdersByNick extends AbstractQueryStrategy<Order> {

	@Override
	public List<Order> executeStrategy() {			
		
		return getServiceLocator().getOrderServices()
				.createNamedQueryListResult("ordersAllByClient", "client_nick", getServiceLocator().getSessionContext().getCallerPrincipal().getName());
									
	}

}
