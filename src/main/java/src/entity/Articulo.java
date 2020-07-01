package src.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ARTICULOS")
public class Articulo implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="pedido_id")
	private Integer pedido_id;
	
	@Column(name="product_id")
	private Integer product_id;

	
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

	public Integer getPedido_id() {
		return pedido_id;
	}

	public void setPedido_id(Integer pedido_id) {
		this.pedido_id = pedido_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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
	
	@Override
	public Articulo clone(){
		
		Articulo nuevo = new Articulo();
		nuevo.setDescripcion(this.getDescripcion());
		nuevo.setPedido_id(this.getPedido_id());
		nuevo.setPrice(this.getPrice());
		nuevo.setProduct_id(this.getProduct_id());
		return nuevo;
	}
	

	
	
	

}
