package src.shopping.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import src.entity.CartItem;
import src.entity.Oferta;
import src.entity.User;
import src.shopping.inter.IValuationManager;

@RequestScoped
public class ValuationManager implements IValuationManager {
	
	private List<CartItem> listaItems;

	@Override
	public Float valuate() {
		Float partialValue = 0f;
		for(CartItem item: listaItems){
			partialValue += ((item.getOferta().getPrecio()) * (item.getCounter()));
		}
		return partialValue;
	}

	public List<CartItem> getListaItems() {
		return listaItems;
	}

	@Override
	public void setListaItems(List<CartItem> listaItems) {
		this.listaItems = listaItems;
	}



}
