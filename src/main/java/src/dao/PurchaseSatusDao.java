package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.PurchaseStatus;

@RequestScoped
public class PurchaseSatusDao extends AbstractDao<PurchaseStatus> {
	

	public PurchaseSatusDao() {
		super(PurchaseStatus.class);
	}

}
