package src.entityservices;

import java.util.List;

import src.inter.IServiceLocator;

public interface IEntityServices<E> {
	
	public E getTransferObject();
	public IServiceLocator getServiceLocator();
	public void create(E e);
	public E read(Integer id);
	public void update(E e);
	public void delete(E e);
	public List<E> readAll();

}
