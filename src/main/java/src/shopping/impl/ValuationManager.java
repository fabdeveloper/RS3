package src.shopping.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import src.entity.Oferta;
import src.entity.User;
import src.shopping.inter.IValuationManager;

@RequestScoped
public class ValuationManager implements IValuationManager {
	
	private List<Oferta> listaItems;

	@Override
	public Float valuate() {
		Float partialValue = 0f;
		for(Oferta oferta: listaItems){
			partialValue += oferta.getPrecio();
		}
		return partialValue;
	}

	public List<Oferta> getListaItems() {
		return listaItems;
	}

	@Override
	public void setListaItems(List<Oferta> listaItems) {
		this.listaItems = listaItems;
	}



}
