package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.Planta;

@RequestScoped
public class PlantaDao extends AbstractDao<Planta> {
	
	public PlantaDao() {
		super(Planta.class);
	}


}
