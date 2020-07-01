package src.wizzard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import src.entity.Articulo;
import src.inter.Carrito;



@SessionScoped
public class CarritoArticulo implements Carrito<Articulo>, Serializable{
	
	private List<Articulo> listaArticulos = new ArrayList<Articulo>();

	@Override
	public void add(Articulo prod) {
		listaArticulos.add(prod);		
	}

	@Override
	public void remove(Articulo prod) {
		listaArticulos.remove(prod);		
	}

	@Override
	public void removeAll() {
		listaArticulos = new ArrayList<Articulo>();		
	}

	@Override
	public void valuate(String strategy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Articulo> getProducts() {
		return listaArticulos;
	}

}
