package src.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.inter.IProcessable;
import src.shopping.inter.IShoppingFacade;
import src.transferobject.EntityViewTransferObject;


@Named
@SessionScoped
public class AvailabilityManagementBB implements Serializable, IProcessable{
	

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private List<Product> listaProductos;
	private List<Articulo> listaArticulos;
	private List<Oferta> listaOfertas;
	
	
	private List<EntityViewTransferObject> listaProductosTO;
	private List<EntityViewTransferObject> listaArticulosTO;
	private List<EntityViewTransferObject> listaOfertasTO;
	
	

	@Override
	public String process(Object obj) {
		Object entity = null;
		String resp = "";
		String type = "";
		String id = "";
		if(obj instanceof String){
			String[] lista = ((String)obj).split("-");
			System.out.println("lista.length = " + lista.length);
			if(lista.length == 2){
				type = lista[0];
				id = lista[1];
			}
			switch(type){
			case "Product":
				for(Product prod : listaProductos){
					if(prod.getId().toString() == id)
					resp = getAvail(prod);
				}
				break;
			case "Articulo":
				for(Articulo prod : listaArticulos){
					if(prod.getId().toString() == id)
					resp = getAvail(prod);
				}
				break;
			case "Oferta":
				for(Oferta prod : listaOfertas){
					if(prod.getId().toString() == id)
					resp = addItemToCart(prod);
				}
				break;
				default:
					;
			
			}
			
		}


		
		return resp;
	}

	public List<EntityViewTransferObject> getListaProductosTO() {
		
		listaProductosTO = new ArrayList<EntityViewTransferObject>();
		EntityViewTransferObject to;
		for(Product item : listaProductos){
			to = new EntityViewTransferObject();
			
			to.setOfid("Product-" + item.getId());
			to.setTexttop(item.getName());
			to.setTextcenter(item.getTipo());
			to.setTextbottom("");
			to.setTextobotonenviar("consulta");			
			to.setUrlimage("image/image" + "Product" + item.getId());			
			
			listaProductosTO.add(to);			
		}
		return listaProductosTO;
	}

	public List<EntityViewTransferObject> getListaArticulosTO() {		
		
		listaArticulosTO = new ArrayList<EntityViewTransferObject>();
		EntityViewTransferObject to;
		for(Articulo item : listaArticulos){
			to = new EntityViewTransferObject();
			
			to.setOfid("Articulo-" + item.getId());
			to.setTexttop(item.getName());
			to.setTextcenter(item.getDescripcion());
			to.setTextbottom(item.getProduct().getName());
			to.setTextobotonenviar("consulta");			
			to.setUrlimage("image/image" + "Articulo" + item.getId());			
			
			listaArticulosTO.add(to);			
		}
		return listaArticulosTO;
	}

	public List<EntityViewTransferObject> getListaOfertasTO() {
		
		listaOfertasTO = new ArrayList<EntityViewTransferObject>();
		EntityViewTransferObject to;
		for(Oferta item : listaOfertas){
			to = new EntityViewTransferObject();
			
			to.setOfid("Oferta-" + item.getId());
			to.setTexttop(item.getName());
			to.setTextcenter(item.getArticulo().getName());
			to.setTextbottom(item.getPrecio().toString());
			to.setTextobotonenviar("consulta");			
			to.setUrlimage("image/image" + "Product" + item.getId());			
			
			listaOfertasTO.add(to);			
		}
		return listaOfertasTO;
	}

	public String getAvail(Object objEntity){
		
		
		return null;		
	}
	
	public String addItemToCart(Oferta item){
		return shoppingFacade.addItemToCart(item);
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
	
	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	
	

}
