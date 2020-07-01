package src.wizzard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.dao.OfertaDao;
import src.entity.Oferta;
import src.inter.Catalogo;

@SessionScoped
public class CatalogoOfertas implements Catalogo<Oferta>, Serializable{
	
	private List<Oferta> listProds = new ArrayList<Oferta>();
	@Inject
	private OfertaDao dao;
	private Oferta ofertaSeleccionada;

	@Override
	public void loadCatalogo(String query) {
		switch(query){
		case "TODO":
			listProds = dao.getAll();
			break;
			default:
				listProds = dao.getAll();
		}		
	}

	@Override
	public List<Oferta> getProducts() {
		return listProds;
	}

	@Override
	public void selectProduct(Oferta prod) {
		ofertaSeleccionada = prod;		
	}

	@Override
	public Oferta getSel() {
		return ofertaSeleccionada;
	}

	@Override
	public Integer countProducts() {
		return listProds.size();
	}

}
