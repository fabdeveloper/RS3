package src.beans;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Pedido;

@Stateless
@Singleton
public class PedidoFacade extends AbstractFacade<Pedido>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public PedidoFacade() {
		super(Pedido.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	

}
