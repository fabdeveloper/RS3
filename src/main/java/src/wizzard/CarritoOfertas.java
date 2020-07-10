package src.wizzard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import src.entity.Oferta;
import src.inter.ICarrito;

@SessionScoped
public class CarritoOfertas implements ICarrito<Oferta>, Serializable {
	
	private List<Oferta> listaOfertas = new ArrayList<Oferta>();

	@Override
	public void add(Oferta prod) {
		listaOfertas.add(prod);		
	}

	@Override
	public void remove(Oferta prod) {
		listaOfertas.remove(prod);		
	}

	@Override
	public void removeAll() {
		listaOfertas = new ArrayList<Oferta>();
	}

	@Override
	public void valuate(String strategy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Oferta> getProducts() {
		return listaOfertas;
	}

}
