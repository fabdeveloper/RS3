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
import src.querystrategy.IQueryStrategyManager;
import src.querystrategy.MasVendidosQS;
import src.querystrategy.QueryStrategyManager;
import src.querystrategy.SugerenciasQS;
import src.querystrategy.UltimasOfertasQS;
import src.shopping.inter.IShoppingFacade;
import src.transferobject.OfertaViewTO;

@Named
@RequestScoped
public class IndexViewBB implements IProcessable{
	
	@Inject
	private IServiceLocator serviceLocator;
	@Inject
	private IShoppingFacade shop;
	
	private IQueryStrategyManager<Oferta, OfertaViewTO> masVendidosQSM;
	private IQueryStrategyManager<Oferta, OfertaViewTO> sugerenciasQSM;
	private IQueryStrategyManager<Oferta, OfertaViewTO> ultimasOfertasQSM;

	
	

	
	/************************************************************************************/
	

	
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

	


	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public List<OfertaViewTO> getListaUltimasOfertasTO() {		
		return getUltimasOfertasQSM().getListaTO();
	}

	public List<OfertaViewTO> getListaMasVendidosArticulosTO() {		
		return getMasVendidosQSM().getListaTO();
	}

	public List<OfertaViewTO> getListaSugerenciasTO() {
		return getSugerenciasQSM().getListaTO();
	}

	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}

	public IQueryStrategyManager<Oferta, OfertaViewTO> getMasVendidosQSM() {
		if(masVendidosQSM == null) {
			masVendidosQSM = new QueryStrategyManager(new MasVendidosQS(serviceLocator));
		}
		return masVendidosQSM;
	}

	public void setMasVendidosQSM(IQueryStrategyManager<Oferta, OfertaViewTO> masVendidosQSM) {
		this.masVendidosQSM = masVendidosQSM;
	}

	public IQueryStrategyManager<Oferta, OfertaViewTO> getSugerenciasQSM() {
		if(sugerenciasQSM == null) {
			sugerenciasQSM = new QueryStrategyManager(new SugerenciasQS(serviceLocator));
		}
		return sugerenciasQSM;
	}

	public void setSugerenciasQSM(IQueryStrategyManager<Oferta, OfertaViewTO> sugerenciasQSM) {
		this.sugerenciasQSM = sugerenciasQSM;
	}

	public IQueryStrategyManager<Oferta, OfertaViewTO> getUltimasOfertasQSM() {
		if(ultimasOfertasQSM == null) {
			ultimasOfertasQSM = new QueryStrategyManager(new UltimasOfertasQS(serviceLocator));
		}
		return ultimasOfertasQSM;
	}

	public void setUltimasOfertasQSM(IQueryStrategyManager<Oferta, OfertaViewTO> ultimasOfertasQSM) {
		this.ultimasOfertasQSM = ultimasOfertasQSM;
	}	
	

}
