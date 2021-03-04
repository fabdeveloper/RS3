package src.entitymanager;

import java.util.List;

import src.entity.Oferta;
import src.entityservices.IEntityServices;
import src.inter.IServiceLocator;

public interface IEntityManager<E> {
	
	public List<E> getAll();
	public List<E> loadAll();
	public E getSelected();
	public Boolean isValidList();
	public E getById(Integer id);
	public E getByName(String name);
	public IEntityServices<E> getEntityServices();
	public default IServiceLocator getServiceLocator(){
		return getEntityServices().getServiceLocator();
	}

}
