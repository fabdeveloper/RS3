package src.backingbean.comp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import src.inter.IListener;
import src.inter.IProcessable;


@Named
@SessionScoped
public class SearchBarCompBB implements Serializable, IProcessable, IListener {
	
	private String text2 = "start";
	
	private String departmentButtonText = "ALL";
	private String productSelected;
	private String articuloSelected;
	private List<String> productList;
	private List<String> articulosList;
	private Boolean productListRendered = false;
	private Boolean articulosListRendered = false;
	
	@PostConstruct
	public void init(){
		productList = new ArrayList<String>();
		articulosList = new ArrayList<String>();
		
		productList.add("product1");
		productList.add("product2");
		
		articulosList.add("articulo 1");
		articulosList.add("articulo 2");
		articulosList.add("articulo 3");		
	}
	
	@Override
	public String process(){
		setProductListRendered(true);
		
		return null;
	}
	
	@Override
	public String listener1(){
		departmentButtonText = productSelected;
		setFacesMessage("producto = " + productSelected);
		setProductListRendered(false);

		return null;
	}
	
	@Override
	public String listener2(){
		
		setFacesMessage("texto = " + text2);
		setArticulosListRendered(true);
		return null;
	}
	
	@Override
	public String listener3(){
		text2 = articuloSelected;
		setFacesMessage("articulo = " + articuloSelected);
		setArticulosListRendered(false);
		return null;
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



}
