package src.wizzard;

import java.util.ArrayList;
import java.util.List;

import src.entity.Oferta;
import src.inter.ICarrito;

public class CarritoOfertas implements ICarrito<Oferta> {
	
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
