package src.entity;

import java.io.Serializable;

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


@Entity
@Table(name="OFERTAS")
@NamedQueries({
	@NamedQuery(name="ofertas por articulo_id", query="SELECT b FROM Oferta b WHERE b.articulo_id LIKE :articulo_id"),
	@NamedQuery(name="ofertas por product_id", query="SELECT b FROM Oferta b WHERE b.product_id LIKE :product_id")
	})
public class Oferta implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="product_id")
	@ManyToOne
	@JoinColumn(table="PRODUCTS", name="ID" )
	private Integer product_id; //FK
	
	@ManyToOne
	@JoinColumn(table="ARTICULOS", name="ID" )
	@Column(name="articulo_id")
	private Integer articulo_id; //FK
	
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
		oferta.setProduct_id(this.getProduct_id());
		oferta.setArticulo_id(this.getArticulo_id());

		return oferta;
	}
	
	
	/********************************************/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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


	public Integer getArticulo_id() {
		return articulo_id;
	}


	public void setArticulo_id(Integer articulo_id) {
		this.articulo_id = articulo_id;
	}
	
	
	
	
	
	

}
