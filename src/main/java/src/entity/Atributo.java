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

import src.inter.Prototype;

@RequestScoped
@Entity
public class Atributo implements Serializable, Prototype<Atributo>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALUE")
	private String value;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ARTICULO") //(name="ARTICULOS_ID")
	private Articulo articulo;
	
	public Atributo clone(){
		Atributo nuevo = new Atributo();
		nuevo.setArticulo(this.getArticulo());
		nuevo.setId(this.getId());
		nuevo.setName(this.getName());
		nuevo.setValue(this.getValue());
		return nuevo;
		
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
