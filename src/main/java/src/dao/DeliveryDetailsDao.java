package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.DeliveryDetails;

@RequestScoped
public class DeliveryDetailsDao extends AbstractDao<DeliveryDetails> {
	
	
	public DeliveryDetailsDao() {
		super(DeliveryDetails.class);
	}

}
