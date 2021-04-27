/**
 * 
 */
package src.querystrategy.orders;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import src.entity.Order;
import src.querystrategy.AbstractQueryStrategyManager;



@SessionScoped
public class OrderQueryStrategyManager extends AbstractQueryStrategyManager<Order> implements Serializable{

}
