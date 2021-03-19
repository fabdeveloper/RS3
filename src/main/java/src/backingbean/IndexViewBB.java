package src.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.CartItem;
import src.entity.Etiqueta;
import src.entity.Oferta;
import src.entity.Order;
import src.inter.IProcessable;
import src.inter.IServiceLocator;
import src.shopping.inter.IShoppingFacade;
import src.transferobject.OfertaViewTO;

@Named
@RequestScoped
public class IndexViewBB implements IProcessable{
	
	private List<Oferta> listaUltimasOfertas;
	private List<Oferta> listaRamdomOfertas;
	private List<Oferta> listaMasVendidosArticulos;
	private List<Oferta> listaSugerencias;
	private List<Oferta> listaPersonal;
	
	
	private List<OfertaViewTO> listaUltimasOfertasTO;
	private List<OfertaViewTO> listaRamdomOfertasTO;
	private List<OfertaViewTO> listaMasVendidosArticulosTO;
	private List<OfertaViewTO> listaSugerenciasTO;
	private List<OfertaViewTO> listaPersonalTO;
	
	@Inject
	private IServiceLocator serviceLocator;
	@Inject
	private IShoppingFacade shop;
	
	
	
	/************************************************************************************/
	
	public String randomAvail() {
		
		// listado de 10 ofertas aleatorias
//		serviceLocator.getOfertaServices().createNamedQueryLimited("", 10);
		
		
		return "";
	}
	

	
	public String ultimasOfertasAvail() {
		
		// listado de las ultimas 10 ofertas creadas		
		listaUltimasOfertas =  serviceLocator.getOfertaServices().createNamedQueryLimited("ofertasAll", 20);
		
		return "";		 
	}
	
	public String masVendidosAvail() {
		
		// listado de los 10 articulos mas vendidos en el ultimo periodo
		List<Order> listaOrdenes = serviceLocator.getOrderServices().createNamedQueryLimited("ordersAll", 10);
		listaMasVendidosArticulos = new ArrayList<Oferta>();
		for(Order order : listaOrdenes) {
			for(CartItem item : order.getCart().getListaItems()) {
				listaMasVendidosArticulos.add(item.getOferta());
			}
		}
		return "";
	}
	
	public String personalAvail() {
		
		// listado de ofertas sobre las ultimas busquedas del usuario
		serviceLocator.getOfertaServices().createNamedQueryLimited("", 10);

		return "";
	}
	
	public String ofertasAvail() {
		
		// listado de ofertas destacadas
		serviceLocator.getOfertaServices().createNamedQueryLimited("", 10);

		return "";
	}
	
	public String sugerenciasAvail() {
		// listado de ofertas sugeridas, navidad, sanvalentin, halloween, etc
		listaSugerencias = new ArrayList<Oferta>();
		
		// etiquetas where startDate < sysdate < stopDate
		List<Etiqueta> etiquetasValidasEncontradas = serviceLocator.getEtiquetaServices().createNamedQueryListResult("etiquetasActivasHoy", null, null);

		for(Etiqueta etiqueta : etiquetasValidasEncontradas) {
			// ofertas where oferta and etiqueta in jointable
			List<Oferta> listaOfertasConEtiqueta = serviceLocator.getOfertaServices().createNamedQueryListResult("ofertasConEtiquetaActiva", null, null);
			for(Oferta oferta : listaOfertasConEtiqueta) {
				listaSugerencias.add(oferta);
			}
		}
		
		return "";
	}
	
	@Override
	public String process(Object obj){
		if(!(obj instanceof String))return null;
		
		publish("obj es un " + obj.getClass().getName());
		publish("obj.toString = " + obj.toString());

		String sId = obj.toString();

		String result = null;
		Integer	id = Integer.valueOf(sId); 
		if(id != null) {
			result = shop.showOfertaDetail(serviceLocator.getOfertaServices().getGestorE().getDao().find(id));			
		}

		

		publish("reult = " + result + ", id = " + id);
		return result;
	}
	
	private void publish(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));

	}

	
	/************************************************************************************/

	
	public List<Oferta> getListaUltimasOfertas() {
		if(listaUltimasOfertas == null) {
			ultimasOfertasAvail();
		}
		return listaUltimasOfertas;
	}

	public void setListaUltimasOfertas(List<Oferta> listaUltimasOfertas) {
		this.listaUltimasOfertas = listaUltimasOfertas;
	}

	public List<Oferta> getListaRamdomOfertas() {
		return listaRamdomOfertas;
	}

	public void setListaRamdomOfertas(List<Oferta> listaRamdomOfertas) {
		this.listaRamdomOfertas = listaRamdomOfertas;
	}

	public List<Oferta> getListaMasVendidosArticulos() {
		if(listaMasVendidosArticulos == null) {
			masVendidosAvail();			
		}
		return listaMasVendidosArticulos;
	}

	public void setListaMasVendidosArticulos(List<Oferta> listaMasVendidosArticulos) {
		this.listaMasVendidosArticulos = listaMasVendidosArticulos;
	}

	public List<Oferta> getListaSugerencias() {		
		if(listaSugerencias == null) {
			sugerenciasAvail();
		}		
		return listaSugerencias;
	}

	public void setListaSugerencias(List<Oferta> listaSugerencias) {
		this.listaSugerencias = listaSugerencias;
	}

	public List<Oferta> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(List<Oferta> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public List<OfertaViewTO> getListaUltimasOfertasTO() {
		if(listaUltimasOfertasTO == null) {
			listaUltimasOfertasTO = new ArrayList<OfertaViewTO>();
			for(Oferta oferta : getListaUltimasOfertas()) {
				listaUltimasOfertasTO.add(OfertaViewTO.getNewOfertaViewTO(oferta));				
			}
		}
		return listaUltimasOfertasTO;
	}

	public void setListaUltimasOfertasTO(List<OfertaViewTO> listaUltimasOfertasTO) {
		this.listaUltimasOfertasTO = listaUltimasOfertasTO;
	}

	public List<OfertaViewTO> getListaRamdomOfertasTO() {
		return listaRamdomOfertasTO;
	}

	public void setListaRamdomOfertasTO(List<OfertaViewTO> listaRamdomOfertasTO) {
		this.listaRamdomOfertasTO = listaRamdomOfertasTO;
	}

	public List<OfertaViewTO> getListaMasVendidosArticulosTO() {
		if(listaMasVendidosArticulosTO == null) {
			listaMasVendidosArticulosTO = new ArrayList<OfertaViewTO>();
			for(Oferta oferta : getListaMasVendidosArticulos()) {
				listaMasVendidosArticulosTO.add(OfertaViewTO.getNewOfertaViewTO(oferta));
			}
		}
		return listaMasVendidosArticulosTO;
	}

	public void setListaMasVendidosArticulosTO(List<OfertaViewTO> listaMasVendidosArticulosTO) {
		this.listaMasVendidosArticulosTO = listaMasVendidosArticulosTO;
	}

	public List<OfertaViewTO> getListaSugerenciasTO() {
		if(listaSugerenciasTO == null) {
			listaSugerenciasTO = new ArrayList<OfertaViewTO>();
			for(Oferta oferta : getListaSugerencias()) {
				listaSugerenciasTO.add(OfertaViewTO.getNewOfertaViewTO(oferta));
			}
		}
		return listaSugerenciasTO;
	}

	public void setListaSugerenciasTO(List<OfertaViewTO> listaSugerenciasTO) {
		this.listaSugerenciasTO = listaSugerenciasTO;
	}

	public List<OfertaViewTO> getListaPersonalTO() {
		return listaPersonalTO;
	}

	public void setListaPersonalTO(List<OfertaViewTO> listaPersonalTO) {
		this.listaPersonalTO = listaPersonalTO;
	}


	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}
	
	

}
