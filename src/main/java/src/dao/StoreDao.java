package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.Store;

@RequestScoped
public class StoreDao extends AbstractDao<Store> {

	public StoreDao() {
		super(Store.class);
	}

}
