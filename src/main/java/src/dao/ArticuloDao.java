package src.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Articulo;

@Stateless
public class ArticuloDao extends AbstractDao<Articulo>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public ArticuloDao() {
		super(Articulo.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
