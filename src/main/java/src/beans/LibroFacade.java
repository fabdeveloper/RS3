package src.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Libro;

@Stateless
public class LibroFacade extends AbstractFacade<Libro>{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager em;

	public LibroFacade() {
		super(Libro.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
//	@Override
	public List<Libro> getAll(){
		
		return getEntityManager().createQuery("SELECT e FROM Libro e").getResultList();
		
	}
}
