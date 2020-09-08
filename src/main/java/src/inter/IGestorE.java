package src.inter;

import src.dao.IDao;
import src.factory.BeanFactory;

public interface IGestorE<T> {
	
	public BeanFactory<T> getFactory();
	public IDao<T> getDao();

}
