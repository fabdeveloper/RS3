package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Oferta;

@RequestScoped
public class OfertaDao extends AbstractDao<Oferta>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public OfertaDao() {
		super(Oferta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
