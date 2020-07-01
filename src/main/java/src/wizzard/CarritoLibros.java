package src.wizzard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import src.entity.Libro;
import src.inter.Carrito;

@SessionScoped
public class CarritoLibros implements Carrito<Libro>, Serializable{
	
	private List<Libro> listaArticulos = new ArrayList<Libro>();

	@Override
	public void add(Libro prod) {
		listaArticulos.add(prod);		
	}

	@Override
	public void remove(Libro prod) {
		listaArticulos.remove(prod);		
	}

	@Override
	public void removeAll() {
		listaArticulos = new ArrayList<Libro>();
	}

	@Override
	public void valuate(String strategy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Libro> getProducts() {
		return listaArticulos;
	}

}
