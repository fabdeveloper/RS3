package src.factory;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import src.entity.Libro;


@RequestScoped
//@Singleton
public class LibroFactory implements ProductFactory<Libro>{
	
	private Libro libro = new Libro();

	@Override
	public Libro crear() {		

		return libro.clone();
	}

	public Libro getLibro() {
		return libro;
	}



	

}
