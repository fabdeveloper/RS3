package src.entity;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="CARTS")
public class Cart implements Serializable, IPrototype<Cart>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
//	@ManyToMany
//	@JoinTable(name="CART_OFERTA",
//	joinColumns=@JoinColumn(name="ID_CART", table="CARTS", referencedColumnName="ID"),
//	inverseJoinColumns=@JoinColumn(name="ID_OFERTA", table="OFERTAS", referencedColumnName="ID"))
//	private List<Oferta> listaOfertas;
	
	@OneToMany(mappedBy="cart")
	private List<CartItem> listaItems;
	
	@Column(name="VALUE")
	private Float value;
	
	

	public Cart clone(){
		Cart nuevo = new Cart();
		nuevo.setId(this.getId());
		nuevo.setValue(this.getValue());
		nuevo.setListaItems(this.getListaItems());
		
		return nuevo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CartItem> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<CartItem> listaItems) {
		this.listaItems = listaItems;
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
	

}
