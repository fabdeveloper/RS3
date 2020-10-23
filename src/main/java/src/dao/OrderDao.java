package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.Order;

@RequestScoped
public class OrderDao extends AbstractDao<Order> {
	
	public OrderDao() {
		super(Order.class);
	}
}
