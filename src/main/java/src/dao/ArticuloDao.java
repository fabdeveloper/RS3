package src.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import src.entity.Articulo;

@RequestScoped
public class ArticuloDao extends AbstractDao<Articulo>{

	public ArticuloDao() {
		super(Articulo.class);
	}

	
	public List<Articulo> getListaArticulosPorProduct_Id(Integer product_id){
		return getEntityManager().createNamedQuery("articulos por product_id", Articulo.class).setParameter("product_id", product_id).getResultList();
	}

}
