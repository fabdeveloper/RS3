package src.backingbean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.util.interfaces.IProcessable;
import src.entity.Oferta;
import src.inter.IServiceLocator;
import src.querystrategy.IQueryStrategy;
import src.querystrategy.IQueryStrategyManager;
import src.querystrategy.MasVendidosQS;
import src.querystrategy.SugerenciasQS;
import src.querystrategy.UltimasOfertasQS;
import src.querystrategy.orders.OfertaQueryStrategyManager;
import src.shopping.inter.IShoppingFacade;
import src.transferobject.OfertaViewTO;

@Named
@RequestScoped
public class IndexViewBB implements IProcessable{
	
	static Logger logger = Logger.getLogger(IndexViewBB.class.getName());

	@Inject
	private IServiceLocator serviceLocator;
	@Inject
	private IShoppingFacade shop;
	
	private IQueryStrategyManager<Oferta> masVendidosQSM;
	private IQueryStrategyManager<Oferta> sugerenciasQSM;
	private IQueryStrategyManager<Oferta> ultimasOfertasQSM;

	
	

	
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
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		logger.log(Level.INFO, msg);
	}

	
	/************************************************************************************/

	


	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public List<OfertaViewTO> getListaUltimasOfertasTO() {		
		return OfertaViewTO.getList(getUltimasOfertasQSM().getList());
	}

	public List<OfertaViewTO> getListaMasVendidosArticulosTO() {		
		return OfertaViewTO.getList(getMasVendidosQSM().getList());

	}

	public List<OfertaViewTO> getListaSugerenciasTO() {
		return OfertaViewTO.getList(getSugerenciasQSM().getList());
	}

	public IShoppingFacade getShop() {
		return shop;
	}

	public void setShop(IShoppingFacade shop) {
		this.shop = shop;
	}

	public IQueryStrategyManager<Oferta> getMasVendidosQSM() {
		if(masVendidosQSM == null) {
			masVendidosQSM = new OfertaQueryStrategyManager();
			IQueryStrategy<Oferta>  masvendidos = new MasVendidosQS(getServiceLocator());
			masVendidosQSM.setStrategy(masvendidos);
		}
		return masVendidosQSM;
	}

	public void setMasVendidosQSM(IQueryStrategyManager<Oferta> masVendidosQSM) {
		this.masVendidosQSM = masVendidosQSM;
	}

	public IQueryStrategyManager<Oferta> getSugerenciasQSM() {
		if(sugerenciasQSM == null) {
			sugerenciasQSM = new OfertaQueryStrategyManager();
			IQueryStrategy<Oferta>  sugerencias = new SugerenciasQS(getServiceLocator());
			sugerenciasQSM.setStrategy(sugerencias);
		}
		return sugerenciasQSM;
	}

	public void setSugerenciasQSM(IQueryStrategyManager<Oferta> sugerenciasQSM) {
		this.sugerenciasQSM = sugerenciasQSM;
	}

	public IQueryStrategyManager<Oferta> getUltimasOfertasQSM() {
		if(ultimasOfertasQSM == null) {
			ultimasOfertasQSM = new OfertaQueryStrategyManager();
			IQueryStrategy<Oferta> ultimasOfertas = new UltimasOfertasQS(getServiceLocator());
			ultimasOfertasQSM.setStrategy(ultimasOfertas);
		}
		return ultimasOfertasQSM;
	}

	public void setUltimasOfertasQSM(IQueryStrategyManager<Oferta> ultimasOfertasQSM) {
		this.ultimasOfertasQSM = ultimasOfertasQSM;
	}	
	

}
