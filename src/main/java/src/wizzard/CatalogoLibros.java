package src.wizzard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.beans.LibroFacade;
import src.entity.Libro;
import src.entity.Product;
import src.inter.Catalogo;

@SessionScoped
public class CatalogoLibros implements Catalogo<Libro>, Serializable{
	
	@Inject
	private LibroFacade libroFacade;
	
	private List<Libro> listaProd = new ArrayList();
	private Libro prodSeleccionado;

	@Override
	public void loadCatalogo(String query) {
		
		switch(query){
		case "TODO":
			listaProd = libroFacade.getAll();	
			break;
			
			default:;

		}		
	}

	@Override
	public List<Libro> getProducts() {
		return listaProd;
	}

	@Override
	public void selectProduct(Libro prod) {
		prodSeleccionado = prod;
	}

	@Override
	public Libro getSel() {
		return prodSeleccionado;
	}

	@Override
	public Integer countProducts() {
		return libroFacade.count();
	}

}
