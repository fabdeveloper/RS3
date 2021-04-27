/**
 * 
 */
package src.querystrategy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import src.entity.CartItem;
import src.entity.Oferta;
import src.entity.Order;
import src.inter.IServiceLocator;

/**
 * @author fabo_
 *
 */



public class MasVendidosQS implements IQueryStrategy<Oferta> {
	
	
	private IServiceLocator serviceLocator;
	
	

	/**
	 * @param serviceLocator
	 */
	public MasVendidosQS(IServiceLocator serviceLocator) {
		super();
		this.serviceLocator = serviceLocator;
	}



	/**
	 * 
	 */
	public MasVendidosQS() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public List<Oferta> executeStrategy() {
		return masVendidosAvail();
	}
	
	
	
	public List<Oferta> masVendidosAvail() {
		
		// listado de los 10 articulos mas vendidos en el ultimo periodo
		List<Order> listaOrdenes = serviceLocator.getOrderServices().createNamedQueryLimited("ordersAll", 10);
		List<Oferta> listaMasVendidosArticulos = new ArrayList<Oferta>();
		for(Order order : listaOrdenes) {
			for(CartItem item : order.getCart().getListaItems()) {
				listaMasVendidosArticulos.add(item.getOferta());
			}
		}
		return listaMasVendidosArticulos;
	}



	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}



	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	
	
	

}
