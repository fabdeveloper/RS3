package src.entitymanager;

import java.util.List;

import javax.inject.Inject;

import src.entityservices.IEntityServices;

public abstract class AbstractEntityManager<E> implements IEntityManager<E> {
	
	private List<E> all;
	private Boolean validList;
	private E selected;
	
	@Inject
	private IEntityServices<E> entityServices;

	@Override
	public List<E> getAll() {
		if(all == null || !isValidList()){
			all = loadAll();
		}
		return all;
	}

	@Override
	public E getSelected() {
		return selected;
	}

	@Override
	public Boolean isValidList() {
		return validList;
	}


	@Override
	public IEntityServices<E> getEntityServices() {
		return entityServices;
	}

	public void setEntityServices(IEntityServices<E> entityServices) {
		this.entityServices = entityServices;
	}

	@Override
	public List<E> loadAll() {
		all = getEntityServices().readAll();
		setValidList(true);
		return all;
	}

	public Boolean getValidList() {
		return validList;
	}

	public void setValidList(Boolean validList) {
		this.validList = validList;
	}

	public void setAll(List<E> all) {
		this.all = all;
	}

	public void setSelected(E selected) {
		this.selected = selected;
	}

}
