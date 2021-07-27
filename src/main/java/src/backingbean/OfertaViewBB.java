package src.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Oferta;
import src.jsfcompslib.util.interfaces.IProcessable;
import src.shopping.inter.IShoppingFacade;


@Named
@RequestScoped
public class OfertaViewBB implements IProcessable {
	
	@Inject
	private IShoppingFacade shop;
	
	private Integer counter = 1;
	
	
	@Transactional
	public String addToCart(){
		return shop.addItemToCart(getOferta(), getCounter());
	}
	
	public Oferta getOferta() {
		return shop.getOfertaSeleccionada();
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}
	
	public Boolean isClient(){
		return shop.isClient();
	}
	
	private String getString(String etiqueta) {		
		return getShop().getString(etiqueta);
	}	
	
	public String getStringAddToCart() {
		return getString("oferta_add_to_cart_buttontext");
	}
	
	public String getStringTextoetiquetadesplegable() {
		return getString("oferta_textoetiquetadesplegable_num_items");
	}
	
	public String getStringLoginadvicetext() {
		return getString("oferta_loginadvicetext");
	}
	
	public String getStringOfertaId() {
		return getString("ofertaviewaux_oferta_code");
	}
	
	public String getStringOfertaName() {
		return getString("ofertaviewaux_oferta_name");
	}
	
	public String getStringOfertaPrice() {
		return getString("ofertaviewaux_oferta_price");
	}
	
	public String getStringOfertaDescription() {
		return getString("ofertaviewaux_oferta_description");
	}
	
	public String getStringOfertaInfo() {
		return getString("ofertaviewaux_oferta_info");
	}
	
	
	
	/***************************************************/
	
	private List<Integer> listaDisponible;
	
	
	public List<Integer> getListaDisponible() {
		if(listaDisponible == null) {
			listaDisponible = new ArrayList<Integer>();
			for (int i = 1; i <= getOferta().getStock(); i++) {
				listaDisponible.add(i);				
			}
		}
		return listaDisponible;
	}

	public void setListaDisponible(List<Integer> listaDisponible) {
		this.listaDisponible = listaDisponible;
	}
	
	
	
	
	@Override
	public Boolean bProcess() {
		return shop.isClient();
	}
	
	@Transactional
	@Override
	public String process() {		
		return addToCart();
	}
	
	@Override
	public String process2() {
		return null;
	}
	

}
