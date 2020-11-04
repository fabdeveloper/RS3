package src.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.shopping.inter.IShoppingFacade;


@Named
@SessionScoped
public class AvailabilityManagementBB implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private List<Product> listaProductos;
	private List<Articulo> listaArticulos;
	private List<Oferta> listaOfertas;
	
	public void getAvail(){
		
	}
	
	public void addItemToCart(){
		shoppingFacade.addItemToCart(item);
	}
	
	@PostConstruct
	public void loadProducts(){
		listaProductos = shoppingFacade.getAvail();		
	}
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Product> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Product> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Articulo> getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(List<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	public List<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(List<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}
	
	

}
