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
import javax.persistence.Table;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="CART_ITEM")
public class CartItem implements Serializable, IPrototype<CartItem> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@JoinColumn(name="OFERTA")
	@ManyToOne
	private Oferta oferta;
	
	@Column(name="COUNTER")
	private Integer counter;
	
	@JoinColumn(name="CART")
	@ManyToOne
	private Cart cart;

	
	public CartItem clone(){
		CartItem nuevo = new CartItem();
		nuevo.setId(this.getId());
		nuevo.setOferta(this.getOferta());
		nuevo.setCounter(this.getCounter());
		nuevo.setCart(this.getCart());
		
		return nuevo;
	}
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
