package src.entity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name="GRUPOS")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME", unique=true)
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Override
	public Grupo clone(){
		
		Grupo nuevo = new Grupo();
		nuevo.setId(this.getId());
		nuevo.setName(this.getName());
		nuevo.setDescription(this.getDescription());

		return nuevo;		
	}
	
	/******************************************************/
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
