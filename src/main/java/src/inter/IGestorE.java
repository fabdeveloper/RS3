package src.inter;

import src.dao.AbstractDao;
import src.factory.BeanFactory;

public interface IGestorE<T> {
	
	public BeanFactory<T> getFactory();
	public AbstractDao<T> getDao();

}
