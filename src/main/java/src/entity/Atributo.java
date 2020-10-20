package src.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Atributo {
	
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
	

}
