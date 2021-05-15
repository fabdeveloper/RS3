/**
 * 
 */
package src.querystrategy.articulos;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import src.entity.Articulo;
import src.querystrategy.AbstractQueryStrategyManager;


@SessionScoped
public class ArticuloQueryStrategyManager extends AbstractQueryStrategyManager<Articulo> implements Serializable {

}
