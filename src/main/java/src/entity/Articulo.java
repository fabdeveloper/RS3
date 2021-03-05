package src.entity;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="ARTICULOS")
@NamedQueries({
	@NamedQuery(name="articuloById", query="SELECT b FROM Articulo b WHERE b.id = :articulo_id"),
	@NamedQuery(name="articuloByName", query="SELECT b FROM Articulo b WHERE b.name = :articulo_name"),
	@NamedQuery(name="articulosByProductId", query="SELECT b FROM Articulo b WHERE b.product.id = :product_id")}
	)
public class Articulo implements Serializable, IPrototype<Articulo>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_PRODUCT") //(name="PRODUCT_ID" )
	private Product product; //FK
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="price")
	private Float price;
	
	@Column(name="URL_IMAGE")
	private String urlImage;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="articulo")
	private List<Atributo> listaAtributos;
	
	
	
	
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
	
	
	
	
	
	

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	@Override
	public Articulo clone(){
		
		Articulo nuevo = new Articulo();
		nuevo.setId(this.getId());
		nuevo.setDescripcion(this.getDescripcion());
		nuevo.setPrice(this.getPrice());
		nuevo.setName(this.getName());
		nuevo.setProduct(this.getProduct());
		nuevo.setListaAtributos(this.getListaAtributos());
		return nuevo;
	}

	public List<Atributo> getListaAtributos() {
		return listaAtributos;
	}

	public void setListaAtributos(List<Atributo> listaAtributos) {
		this.listaAtributos = listaAtributos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
	

}
