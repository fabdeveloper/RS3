package src.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Articulo;

@RequestScoped
//@Stateless
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
	
	public List<Articulo> getListaArticulosPorProduct_Id(Integer product_id){
		return getEntityManager().createNamedQuery("articulos por product_id", Articulo.class).setParameter("product_id", product_id).getResultList();
	}

}
