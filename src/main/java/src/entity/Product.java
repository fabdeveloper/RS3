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
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="PRODUCTS")
@NamedQueries({
	@NamedQuery(name="productos por nombre", query="SELECT b FROM Product b WHERE b.name LIKE :name"),
	@NamedQuery(name="productos por tipo", query="SELECT b FROM Product b WHERE b.tipo LIKE :tipo")}	
	)
//@MappedSuperclass
public class Product implements Serializable, IPrototype<Product>{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	@OneToMany(mappedBy="product")
	private Integer id;
	
	@Column(name="NAME", unique=true)
	private String name;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="URL_IMAGE")
	private String urlImage;

//	@ManyToOne
//	@JoinColumn(name="FK_STORE")
//	private Store store;
	
	
	/***************************************/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
//	public Store getStore() {
//		return store;
//	}
//
//	public void setStore(Store store) {
//		this.store = store;
//	}
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	/*******************************************/

	@Override
	public Product clone(){
		
		Product nuevo = new Product();
		nuevo.setId(this.getId());
		nuevo.setName(this.getName());
		nuevo.setTipo(this.getTipo());
//		nuevo.setStore(this.getStore());
		
		return nuevo;
		
	}


	
	
	

}
