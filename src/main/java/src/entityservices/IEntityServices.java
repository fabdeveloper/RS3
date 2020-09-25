package src.entityservices;

import java.util.List;

public interface IEntityServices<E> {
	
	public E getTransferObject();
	public void create(E e);
	public E read(Integer id);
	public void update(E e);
	public void delete(E e);
	public List<E> readAll();

}
