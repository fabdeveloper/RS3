package src.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Grupo;

@RequestScoped
public class GrupoDao extends AbstractDao<Grupo> {
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public GrupoDao() {
		super(Grupo.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
