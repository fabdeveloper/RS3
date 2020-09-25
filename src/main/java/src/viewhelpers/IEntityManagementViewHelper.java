package src.viewhelpers;

import java.util.List;

public interface IEntityManagementViewHelper<E> {
	
	// metodos para implementar en la clase abstracta
	
	public E getTransferObject();
	public void create(E e);
	public E read(Integer id);
	public void update(E e);
	public void delete(E e);
	public List<E> readAll();
	
	
	// metodos para implementar en el view helper
	
	public void callCreate(); // llama a create
	public E callRead(); // llama a read
	public void callUpdate(); // llama a update
	public void callDelete(); // llama a delete
	

}
