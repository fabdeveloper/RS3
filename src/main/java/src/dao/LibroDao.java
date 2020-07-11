package src.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Libro;

//@Stateless
@RequestScoped
public class LibroDao extends AbstractDao<Libro> {
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public LibroDao() {
		super(Libro.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
