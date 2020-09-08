package src.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import src.entity.Oferta;

@RequestScoped
public class OfertaDao extends AbstractDao<Oferta>{

	public OfertaDao() {
		super(Oferta.class);
	}
	
	public List<Oferta> getListaOfertasPorArticulo_Id(Integer articulo_id) {
		return getEntityManager().createNamedQuery("ofertas por articulo_id", Oferta.class).setParameter("articulo_id", articulo_id).getResultList();
		
	}
	
	public List<Oferta> getListaOfertasPorProduct_Id(Integer product_id) {
		return getEntityManager().createNamedQuery("ofertas por product_id", Oferta.class).setParameter("product_id", product_id).getResultList();
		
	}
	
	

}
