package src.factory;

import javax.enterprise.context.RequestScoped;

import src.entity.Planta;

@RequestScoped
public class PlantaFactory implements BeanFactory<Planta>{
	
	private Planta planta = new Planta();

	@Override
	public Planta crear() {
		return planta.clone();
	}

}
