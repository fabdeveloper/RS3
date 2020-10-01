package src.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import src.inter.Prototype;

@RequestScoped
@Entity
@Table(name="OFERTAS")
@NamedQueries({
	@NamedQuery(name="ofertas por articulo_id", query="SELECT b FROM Oferta b WHERE b.articulo.id = :articulo_id"),
	@NamedQuery(name="ofertas por product_id", query="SELECT b FROM Oferta b WHERE b.articulo.product.id = :product_id")
	})
public class Oferta implements Serializable, Prototype<Oferta>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	

	
	@ManyToOne
	@JoinColumn(name="ARTICULO_ID" )
	private Articulo articulo; //FK
	
	@Column(name="precio")
	private Float precio;
	
	@Column(name="descripcion")
	private String descripcion;

	
	
	
	@Override
	public Oferta clone(){
		Oferta oferta = new Oferta();
		oferta.setId(this.getId());
		oferta.setName(this.getName());

		oferta.setDescripcion(this.getDescripcion());
		oferta.setPrecio(this.getPrecio());
		oferta.setArticulo(this.getArticulo());

		return oferta;
	}
	
	
	/********************************************/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Articulo getArticulo() {
		return articulo;
	}


	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	
	
	
	
	
	

}
