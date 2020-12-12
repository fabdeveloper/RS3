package src.backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Oferta;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class OfertaViewBB {
	
	@Inject
	private IShoppingFacade shop;
	
	private Oferta oferta;
	
	
	
	
	public String addToCart(){
		return shop.addItemToCart(getOferta());
	}
	
	
	
	
	
	

	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}

	public Oferta getOferta() {
		if(oferta == null){
			oferta = shop.getOfertaSeleccionada();
		}
		return oferta;
	}


	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
	

}
