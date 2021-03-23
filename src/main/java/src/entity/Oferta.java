package src.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="OFERTAS")
@NamedQueries({
	@NamedQuery(name="ofertaById", query="SELECT b FROM Oferta b WHERE b.id = :oferta_id"),
	@NamedQuery(name="ofertaByName", query="SELECT b FROM Oferta b WHERE b.name = :oferta_name"),
	@NamedQuery(name="ofertasByArticuloId", query="SELECT b FROM Oferta b WHERE b.articulo.id = :articulo_id"),
	@NamedQuery(name="ofertasByProductId", query="SELECT b FROM Oferta b WHERE b.articulo.product.id = :product_id"),
	@NamedQuery(name="ofertasAll", query="SELECT b FROM Oferta b ORDER BY b.id DESC"),
	@NamedQuery(name="ofertasConEtiquetaActiva", query="SELECT b FROM Oferta b inner join b.listaEtiquetas a  WHERE a.id = :etiqueta_id")
	

//	select distinct d from Document d inner join d.accessors a where a.id = :id
	
	
	})
public class Oferta implements Serializable, IPrototype<Oferta>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="ARTICULO_ID" )
	private Articulo articulo; //FK
	
	@Column(name="PRECIO")
	private Float precio;
	
	@Column(name="DESCRIPCION")
	private String descripcion;

	@Column(name="URL_IMAGE")
	private String urlImage;
	
	
	@Column(name="URL_IMAGE_BIG")
	private String urlImagebig;
	
	@Column(name="STOCK")
	private Integer stock;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;

	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;
	
	@ManyToMany
	@JoinTable(name="ETIQUETA_OFERTA",
			joinColumns=@JoinColumn(name="OFERTA_ID", table="OFERTAS", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="ETIQUETA_ID", table="ETIQUETAS", referencedColumnName="ID"))
	private List<Etiqueta> listaEtiquetas;
	
	
	
	
	@Override
	public Oferta clone(){
		Oferta oferta = new Oferta();
		oferta.setId(this.getId());
		oferta.setName(this.getName());
		oferta.setDescripcion(this.getDescripcion());
		oferta.setPrecio(this.getPrecio());
		oferta.setArticulo(this.getArticulo());
		oferta.setUrlImage(this.getUrlImage());
		oferta.setUrlImagebig(this.getUrlImagebig());
		oferta.setCreationDate(this.getCreationDate());
		oferta.setExpirationDate(this.getExpirationDate());
		oferta.setListaEtiquetas(this.getListaEtiquetas());


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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	public String getUrlImagebig() {
		return urlImagebig;
	}

	public void setUrlImagebig(String urlImagebig) {
		this.urlImagebig = urlImagebig;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public List<Etiqueta> getListaEtiquetas() {
		return listaEtiquetas;
	}


	public void setListaEtiquetas(List<Etiqueta> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}
	
	public void addEtiqueta(Etiqueta etiqueta) {
		if(listaEtiquetas == null) {
			listaEtiquetas = new ArrayList<Etiqueta>();
		}
		listaEtiquetas.add(etiqueta);
		
	}

}
