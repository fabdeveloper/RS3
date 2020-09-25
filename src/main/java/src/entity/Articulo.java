package src.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name="ARTICULOS")
@NamedQueries({
	@NamedQuery(name="articulos por product_id", query="SELECT b FROM Articulo b WHERE b.product.id = :product_id")}
	)
public class Articulo implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID" )
	private Product product; //FK
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="price")
	private Float price;
	
	
	
	
	/****************************************/
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	

	@Override
	public Articulo clone(){
		
		Articulo nuevo = new Articulo();
		nuevo.setId(this.getId());
		nuevo.setDescripcion(this.getDescripcion());
		nuevo.setPrice(this.getPrice());
		nuevo.setName(this.getName());
		nuevo.setProduct(this.getProduct());
		return nuevo;
	}
	

	
	
	

}
