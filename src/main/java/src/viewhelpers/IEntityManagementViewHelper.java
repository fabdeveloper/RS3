package src.viewhelpers;

import java.util.List;

import src.entityservices.IEntityServices;
import src.inter.IServiceLocator;

public interface IEntityManagementViewHelper<E> {
	
	// metodos para implementar en la clase abstracta
	
	public E getTransferObject();
	public void setTransferObject(E e);
	public IEntityServices<E> getEntityServices();
	public IServiceLocator getServiceLocator();
	public void create();
	public E read();
	public void update();
	public void delete();
	public List<E> readAll();
	
	
	// metodos para implementar en el view helper
	
	public Integer getId();

	

}
