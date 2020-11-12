package src.entity;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import src.inter.Prototype;

@RequestScoped
@Entity
@Table(name="CARTS")
public class Cart implements Serializable, Prototype<Cart>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
//	@JoinColumn(name="LISTAOFERTAS")
	@ManyToMany
	@JoinTable(name="CART_OFERTA",
	joinColumns=@JoinColumn(name="ID_CART", table="CARTS", referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name="ID_OFERTA", table="OFERTAS", referencedColumnName="ID"))
	private List<Oferta> listaOfertas;
	
	@Column(name="VALUE")
	private Float value;
	
	@OneToOne
	@JoinColumn(name="ORDER")
	@PrimaryKeyJoinColumn(name="Id")
	private Order order;

	public Cart clone(){
		Cart nuevo = new Cart();
		nuevo.setId(this.getId());
		nuevo.setValue(this.getValue());
		nuevo.setListaOfertas(this.getListaOfertas());
		nuevo.setOrder(this.getOrder());
		
		
		return nuevo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(List<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
