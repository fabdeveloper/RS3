package src.viewhelpers;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import src.entity.User;
import src.entityservices.IEntityServices;

public abstract class AbstractEntityManagementViewHelper<E> implements
		IEntityManagementViewHelper<E> {
	
	@Inject
	private E transferObject;
	@Inject
	private IEntityServices<E> entityServices;


	@Override
	public E getTransferObject(){
		return transferObject;
	}
	
	@Override
	public void setTransferObject(E e){
		transferObject = e;		
	}


	@Transactional
	@Override
	public void create() {
		entityServices.create(getTransferObjectClone());		
	}
	
	@Override
	public E read() {
		return entityServices.read(getId());
	}

	@Transactional
	@Override
	public void update() {
		entityServices.update(getTransferObjectClone());		
	}

	@Transactional
	@Override
	public void delete() {
		entityServices.delete(getTransferObjectClone());			
	}
	
	@Override
	public List<E> readAll() {
		return entityServices.readAll();
	}

}
