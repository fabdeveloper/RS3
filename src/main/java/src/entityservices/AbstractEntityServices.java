package src.entityservices;

import java.util.List;

import javax.inject.Inject;
import src.gestor.IGestorE;

public abstract class AbstractEntityServices<E> implements IEntityServices<E> {
	
	@Inject
	private IGestorE<E> gestor;

	@Override
	public E getTransferObject() {
		return gestor.getFactory().crear();
	}

	@Override
	public void create(E e) {
		gestor.getDao().create(e);
	}

	@Override
	public E read(Integer id) {
		return gestor.getDao().find(id);		
	}

	@Override
	public void update(E e) {
		gestor.getDao().edit(e);
	}

	@Override
	public void delete(E e) {
		gestor.getDao().remove(e);
	}

	@Override
	public List<E> readAll() {
		return gestor.getDao().getAll();
	}

}
