package src.factory;

import javax.enterprise.context.RequestScoped;

import src.entity.Articulo;


@RequestScoped
public class ArticuloFactory implements ProductFactory<Articulo>{
	
	Articulo articulo = new Articulo();

	@Override
	public Articulo crear() {
		return articulo.clone();
	}

}