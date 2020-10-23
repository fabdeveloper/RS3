package src.factory;

import javax.enterprise.context.RequestScoped;

import src.entity.Order;

@RequestScoped
public class OrderFactory extends AbstractBeanFactory<Order> {

}
