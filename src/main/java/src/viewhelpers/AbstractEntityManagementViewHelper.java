package src.viewhelpers;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import src.entity.User;
import src.entityservices.IEntityServices;
import src.inter.IServiceLocator;

public abstract class AbstractEntityManagementViewHelper<E> implements
		IEntityManagementViewHelper<E> {
	
	
	private E transferObject;
	@Inject
	private IEntityServices<E> entityServices;


	@Override
	public E getTransferObject(){
		if(transferObject == null){
			transferObject = entityServices.getTransferObject();
		}
		return transferObject;
	}
	
	@Override
	public void setTransferObject(E e){
		transferObject = e;		
	}
	
	@Override
	public IEntityServices<E> getEntityServices(){
		return entityServices;
	}
	
	@Override
	public IServiceLocator getServiceLocator(){
		return entityServices.getServiceLocator();
	}


	@Transactional
	@Override
	public void create() {
		entityServices.create(getTransferObject());		
	}
	
	@Override
	public E read() {
		return entityServices.read(getId());
	}

	@Transactional
	@Override
	public void update() {
		entityServices.update(getTransferObject());		
	}

	@Transactional
	@Override
	public void delete() {
		entityServices.delete(getTransferObject());			
	}
	
	@Override
	public List<E> readAll() {
		return entityServices.readAll();
	}

}
