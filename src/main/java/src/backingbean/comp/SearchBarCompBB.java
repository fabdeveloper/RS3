package src.backingbean.comp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.inter.IListener;
import src.inter.IProcessable;
import src.querystrategy.AbstractQueryStrategy;
import src.querystrategy.articulos.ArticuloQueryStrategyManager;
import src.querystrategy.orders.OfertaQueryStrategyManager;
import src.shopping.impl.ShoppingFacade;
import src.shopping.inter.IShoppingFacade;
import src.transferobject.EntityViewTransferObject;



@Named
@SessionScoped
public class SearchBarCompBB implements Serializable, IProcessable, IListener {
	
	static Logger logger = Logger.getLogger(SearchBarCompBB.class.getName());

	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private String text2 = "";
	
	private String departmentButtonText = null; // "ALL";
	private String productSelected;
	private String articuloSelected;
	private List<String> productList;
	private List<String> articulosList;
	private Boolean productListRendered = false;
	private Boolean articulosListRendered = false;
	
	private String buttonText = null; // "Search";
	
	private List<EntityViewTransferObject> ofertaList;	

	@Inject
	private ArticuloQueryStrategyManager articuloQSM;
	@Inject
	private OfertaQueryStrategyManager ofertaQSM;
	
	
	@Override
	public String process(Object obj) {
		return "";
	}
	
	
	/*
	 * CUANDO CLICK ENEL BOTON ALL DE SEARCHBAR
	 */
	@Override
	public String process(){ // carga y muestra lista de productos
		if(productList == null){
			initProductList(); 
		}
		setProductListRendered(true);
		
		return null;
	}
	
	/**
	 * 
	 */
	private void initProductList() {
		productList = new ArrayList<String>();
		for(Product prod : getShoppingFacade().getDepartmentStoreList()){
			productList.add(prod.getName());
		}		
	}


	/*
	 * CUANDO CLICK EN LA LISTA DE PRODUCTOS
	 */
	@Override
	public String listener1(){
		// seleccionado un producto de la lista de productos
		departmentButtonText = productSelected;
		logger.log(Level.INFO, "producto = " + productSelected);
		
		setProductListRendered(false);		
		initArticulosListByProduct();
		setArticulosListRendered(true);

		return null;
	}
	
	/**
	 * 
	 */
	private void initArticulosListByProduct() {
		// cargar la lista de articulos
		initArticulosList(shoppingFacade.getArticulosByProductName(productSelected));	
	}


	/*
	 * CUANDO EVENT EN INPUTTEXT
	 */
	@Override
	public String listener2(){ // muestra la lista de articulos
		
		logger.log(Level.INFO, "texto = " + text2);		
		
		// crear una QueryStrategy de text2 
		AbstractQueryStrategy<Articulo> busquedatemporal = new  AbstractQueryStrategy<Articulo>() {
			
			@Override
			public List<Articulo> executeStrategy() {
				return getShoppingFacade().getServiceLocator().getArticuloServices().createNamedQueryListResult("articulosByString", "some_text", '%'+text2+'%');
			}
		};
		
		
		// inicializar un QueryManager
		
		getArticuloQSM().setStrategy(busquedatemporal);
		getArticuloQSM().reset();
		
		// cargar la lista de articulos
		
		initArticulosListByQSM();
		
		if(!getArticulosListRendered())setArticulosListRendered(true);
		return null;
	}
	
	/**
	 * 
	 */
	public void initArticulosListByQSM() {
		initArticulosList(getArticuloQSM().getList());		
	}
	
	public void initArticulosList(List<Articulo> listaArticulos) {
		articulosList = new ArrayList<String>();
		for(Articulo art : listaArticulos){
			articulosList.add(art.getName());
		}	
	}


	@Override
	public String listener3(){
		// selecciona un articulo de la lista
		text2 = articuloSelected;
		logger.log(Level.INFO, "articulo = " + articuloSelected);
		setArticulosListRendered(false);
		
		ofertaList = new ArrayList<EntityViewTransferObject>();
		EntityViewTransferObject trans;
		// carga la lista de ofertas
		getOfertaQSM().setStrategy(new AbstractQueryStrategy<Oferta>() {

			@Override
			public List<Oferta> executeStrategy() {
				return getShoppingFacade().getServiceLocator().getOfertaServices().createNamedQueryListResult("ofertasByArticuloName", "articulo_name", '%' + articuloSelected + '%');
			}
		});
		getOfertaQSM().reset();
		
//		for(Oferta oferta : getShoppingFacade().getAvailabilityManager().getOfertasByArticuloName(articuloSelected)) { mm

		for(Oferta oferta : getOfertaQSM().getList()) { 
			trans = new EntityViewTransferObject();
			trans.setOfid(oferta.getId().toString());
			trans.setTextbottom("compra");
			trans.setTextcenter(oferta.getName());
			trans.setTextobotonenviar("aquí");
			trans.setTexttop(oferta.getArticulo().getName());
			trans.setUrlimage(oferta.getUrlImage());
			ofertaList.add(trans);  
		}
		
		
		
//		return shoppingFacade.getOfertasByArticuloName(articuloSelected);
		return "";
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}
	
	
	public String getProductSelected() {
		return productSelected;
	}

	public void setProductSelected(String productSelected) {
		this.productSelected = productSelected;
	}

	public String getArticuloSelected() {
		return articuloSelected;
	}

	public void setArticuloSelected(String articuloSelected) {
		this.articuloSelected = articuloSelected;
	}

	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public List<String> getArticulosList() {
		return articulosList;
	}

	public void setArticulosList(List<String> articulosList) {
		this.articulosList = articulosList;
	}

//	public void setFacesMessage(String msg){
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
//pp
//	}

	public String getDepartmentButtonText() {
		if(departmentButtonText == null) {
			departmentButtonText = getShoppingFacade().getString("searchbar_allbuttontext");
		}
		return departmentButtonText;
	}

	public void setDepartmentButtonText(String departmentButtonText) {
		this.departmentButtonText = departmentButtonText;
	}

	public Boolean getProductListRendered() {
		return productListRendered;
	}

	public void setProductListRendered(Boolean productListRendered) {
		this.productListRendered = productListRendered;
	}

	public Boolean getArticulosListRendered() {
		return articulosListRendered;
	}

	public void setArticulosListRendered(Boolean articulosListRendered) {
		this.articulosListRendered = articulosListRendered;
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}

	public List<EntityViewTransferObject> getOfertaList() {
		return ofertaList;
	}

	public void setOfertaList(List<EntityViewTransferObject> ofertaList) {
		this.ofertaList = ofertaList;
	}

	public String getButtonText() {
		if(buttonText == null) {
			buttonText = getShoppingFacade().getString("searchbar_searchbuttontext");
		}
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	
	
	
	public ArticuloQueryStrategyManager getArticuloQSM() {
		return articuloQSM;
	}


	public void setArticuloQSM(ArticuloQueryStrategyManager articuloQSM) {
		this.articuloQSM = articuloQSM;
	}


	public OfertaQueryStrategyManager getOfertaQSM() {
		return ofertaQSM;
	}

	public void setOfertaQSM(OfertaQueryStrategyManager ofertaQSM) {
		this.ofertaQSM = ofertaQSM;
	}


	@Override
	public String process3() {		// SearchButton onClick()
		logger.log(Level.INFO, "articulo seleccionado = " + getText2());
		return shoppingFacade.showAvail(getText2());
		
	}




}
