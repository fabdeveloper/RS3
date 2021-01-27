package src.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Oferta;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class OfertaViewBB {
	
	@Inject
	private IShoppingFacade shop;
	
	private Integer counter = 1;
	
//	private Oferta oferta;
	
	
	
	@Transactional
	public String addToCart(){
		return shop.addItemToCart(getOferta(), getCounter());
	}
	

	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}

	public Oferta getOferta() {
//		if(oferta == null){
//			oferta = shop.getOfertaSeleccionada();
//		}
//		return oferta;

		return shop.getOfertaSeleccionada();

	}


	public Integer getCounter() {
		return counter;
	}


	public void setCounter(Integer counter) {
		this.counter = counter;
	}


//	public void setOferta(Oferta oferta) {
//		this.oferta = oferta;
//	}
	
	
	
	

}
