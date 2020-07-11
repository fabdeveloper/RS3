package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Planta;

//@Stateless
@RequestScoped
public class PlantaDao extends AbstractDao<Planta> {
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public PlantaDao() {
		super(Planta.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
