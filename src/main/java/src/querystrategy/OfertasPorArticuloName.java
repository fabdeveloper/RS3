/**
 * 
 */
package src.querystrategy;

import java.util.List;

import src.entity.Oferta;

/**
 * @author fabo_
 *
 */
public class OfertasPorArticuloName extends AbstractQueryStrategy {

	@Override
	public List<Oferta> executeStrategy() {
		return getServiceLocator().getOfertaServices().createNamedQueryListResult("ofertasByArticuloName", "articulo_name", getParametro());
	}

}
