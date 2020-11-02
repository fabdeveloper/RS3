package src.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import src.inter.Prototype;

@RequestScoped
@Entity
@Table(name="STORES")
public class Store implements Serializable, Prototype<Store>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME", unique=true)
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
//	@OneToOne//(mappedBy="id")
//	@JoinColumn(name="id")
//	private User owner;
	
//	@OneToMany(cascade=CascadeType.ALL, mappedBy="store")
//	private List<Grupo> listaGrupos;

	
	/**********************************************/
	//GETTERS AND SETTERS
	
	
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

//	public User getOwner() {
//		return owner;
//	}
//
//	public void setOwner(User owner) {
//		this.owner = owner;
//	}	

//	public List<Grupo> getListaGrupos() {
//		return listaGrupos;
//	}
//
//	public void setListaGrupos(List<Grupo> listaGrupos) {
//		this.listaGrupos = listaGrupos;
//	}	
//	
//	public void addGrupo(Grupo grupo){
//		if(getListaGrupos() == null){
//			setListaGrupos(new ArrayList<Grupo>());
//		}
//		this.listaGrupos.add(grupo);
//	}
//	
//	public void removeGrupo(Grupo grupo){
//		this.listaGrupos.remove(grupo);
//	}
	
	/*******************************************/
	
	@Override
	public Store clone(){
		Store nuevo = new Store();
		
		nuevo.setId(this.getId());
		nuevo.setName(this.getName());
		nuevo.setDescription(this.getDescription());
//		nuevo.setOwner(this.getOwner());
//		nuevo.setListaGrupos(this.listaGrupos);
		
		return nuevo;
	}
	

	
	

}
