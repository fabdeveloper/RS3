package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Pedido;

//@Stateless
@RequestScoped
public class PedidoDao extends AbstractDao<Pedido>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public PedidoDao() {
		super(Pedido.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
