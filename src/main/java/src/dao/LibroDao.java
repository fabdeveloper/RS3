package src.dao;

import javax.enterprise.context.RequestScoped;
import src.entity.Libro;

@RequestScoped
public class LibroDao extends AbstractDao<Libro> {

	public LibroDao() {
		super(Libro.class);
	}

}
