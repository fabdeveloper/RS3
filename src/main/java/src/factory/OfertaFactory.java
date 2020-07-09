package src.factory;

import javax.enterprise.context.RequestScoped;

import src.entity.Oferta;

@RequestScoped
public class OfertaFactory implements BeanFactory<Oferta>{
	
	private Oferta oferta = new Oferta();

	@Override
	public Oferta crear() {
		return oferta.clone();
	}

}
