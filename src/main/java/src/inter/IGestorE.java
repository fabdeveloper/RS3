package src.inter;

import src.dao.AbstractDao;
import src.factory.ProductFactory;

public interface IGestorE<T> {
	
	public ProductFactory<T> getFactory();
	public AbstractDao<T> getDao();

}
