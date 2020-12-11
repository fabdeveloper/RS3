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
		return shop.addItemToCart(oferta);
	}
	
	
	
	
	
	

	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}

	public Oferta getOferta() {
		return oferta;
	}
	
	@PostConstruct
	public void setOferta() {
		this.oferta = shop.getOfertaSeleccionada();
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
	

}
