package src.entitymanager;

import java.util.List;

import src.entity.Oferta;
import src.entityservices.IEntityServices;
import src.inter.IServiceLocator;

public interface IEntityManager<E> {
	
	public List<E> getAll();
	public void setAll(List<E> list);
	public List<E> loadAll();
	public E getSelected();
	public void setSelected(E e);
	public Boolean isValidList();
	public void setValidList(Boolean isvalid);
	public E getById(Integer id);
	public E getByName(String name);
	public IEntityServices<E> getEntityServices();
	public default IServiceLocator getServiceLocator(){return getEntityServices().getServiceLocator();}

}
