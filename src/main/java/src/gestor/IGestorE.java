package src.gestor;

import src.dao.IDao;
import src.factory.IBeanFactory;

public interface IGestorE<T> {
	
	public IBeanFactory<T> getFactory();
	public IDao<T> getDao();

}
