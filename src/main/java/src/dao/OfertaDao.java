package src.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Oferta;

@RequestScoped
//@Stateless
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
	
	public List<Oferta> getListaOfertasPorArticulo_Id(Integer articulo_id) {
		return getEntityManager().createNamedQuery("ofertas por articulo_id", Oferta.class).setParameter("articulo_id", articulo_id).getResultList();
		
	}
	
	public List<Oferta> getListaOfertasPorProduct_Id(Integer product_id) {
		return getEntityManager().createNamedQuery("ofertas por product_id", Oferta.class).setParameter("product_id", product_id).getResultList();
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
