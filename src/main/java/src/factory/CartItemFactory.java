package src.factory;

import javax.enterprise.context.RequestScoped;

import src.entity.CartItem;

@RequestScoped
public class CartItemFactory extends AbstractBeanFactory<CartItem> {

}
