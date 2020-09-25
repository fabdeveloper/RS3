package src.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@RequestScoped
@Entity
@Table(name="PLANTAS")
public class Planta extends Articulo implements Serializable{
	
	@Column(name="NOMBRE_COMERCIAL")
	private String nombreComercial;
	@Column(name="ESPECIE")
	private String especie;
	
	
	
	@Override
	public Planta clone(){
		
		Planta nuevo = new Planta();
		nuevo.setId(this.getId());
		nuevo.setDescripcion(this.getDescripcion());
		nuevo.setPrice(this.getPrice());
		nuevo.setName(this.getName());
		nuevo.setProduct(this.getProduct());
		nuevo.setEspecie(this.getEspecie());
		nuevo.setNombreComercial(this.getNombreComercial());
		return nuevo;
	}
	
	
	
	
	//***  GETTERS - SETTERS          *****************
	
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	
	
	
	

}
