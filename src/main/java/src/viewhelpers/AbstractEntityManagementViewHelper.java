package src.viewhelpers;

import java.util.List;

import javax.inject.Inject;

import src.entityservices.IEntityServices;

public abstract class AbstractEntityManagementViewHelper<E> implements
		IEntityManagementViewHelper<E> {
	
	@Inject
	private E transferObject;
	@Inject
	private IEntityServices<E> entityServices;

	@Override
	public E getTransferObject() {
		return transferObject;
	}

	@Override
	public void create(E e) {
		entityServices.create(e);
	}

	@Override
	public E read(Integer id) {
		return entityServices.read(id);
	}

	@Override
	public void update(E e) {
		entityServices.update(e);		
	}

	@Override
	public void delete(E e) {
		entityServices.delete(e);		
	}

	@Override
	public List<E> readAll() {
		return entityServices.readAll();
	}

}
