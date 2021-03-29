package src.backingbean.comp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import src.shopping.inter.IShoppingFacade;
import src.transferobject.EntityViewTransferObject;


@Named
@SessionScoped
public class SearchBarCompBB implements Serializable, IProcessable, IListener {
	
	@Inject
	private IShoppingFacade shoppingFacade;
	
	private String text2 = "start";
	
	private String departmentButtonText = "ALL";
	private String productSelected;
	private String articuloSelected;
	private List<String> productList;
	private List<String> articulosList;
	private Boolean productListRendered = false;
	private Boolean articulosListRendered = false;
	
	private String buttonText = "buy it now";
	
	private List<EntityViewTransferObject> ofertaList;
	
	@PostConstruct
	public void init(){
//		productList = new ArrayList<String>();		
//		productList.add("product1");
//		productList.add("product2");
		
		articulosList = new ArrayList<String>();

		articulosList.add("articulo 1");
		articulosList.add("articulo 2");
		articulosList.add("articulo 3");		
	}
	
	@Override
	public String process(Object obj) {
		return "";
	}
	
	@Override
	public String process(){ // carga y muestra lista de productos
		if(productList == null){
			productList = new ArrayList<String>();
			for(Product prod : getShoppingFacade().getDepartmentStoreList()){
				productList.add(prod.getName());
			} 
		}
		setProductListRendered(true);
		
		return null;
	}
	
	@Override
	public String listener1(){
		// seleccionado un producto de la lista de productos
		departmentButtonText = productSelected;
		setFacesMessage("producto = " + productSelected);
		setProductListRendered(false);
		
		// cargar la lista de articulos
		articulosList = new ArrayList<String>();
		for(Articulo art : shoppingFacade.getArticulosByProductName(productSelected)){
			articulosList.add(art.getName());
		}
		return null;
	}
	
	@Override
	public String listener2(){ // muestra la lista de articulos
		
		setFacesMessage("texto = " + text2);
		setArticulosListRendered(true);
		return null;
	}
	
	@Override
	public String listener3(){
		// selecciona un articulo de la lista
		text2 = articuloSelected;
		setFacesMessage("articulo = " + articuloSelected);
		setArticulosListRendered(false);
		
		ofertaList = new ArrayList<EntityViewTransferObject>();
		EntityViewTransferObject trans;
		// carga la lista de ofertas
		for(Oferta oferta : shoppingFacade.getAvailabilityManager().getOfertasByArticuloName(articuloSelected)) {
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

	public void setFacesMessage(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));

	}

	public String getDepartmentButtonText() {
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
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	
	@Override
	public String process3() {		
		return shoppingFacade.showAvail(articuloSelected);
		
	}




}
