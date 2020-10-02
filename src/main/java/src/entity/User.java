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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import src.inter.IUser;
import src.inter.Prototype;

@RequestScoped
@Entity
@Table(name="USERS")
@NamedQueries({
	@NamedQuery(name="byName", query="SELECT u FROM User u WHERE u.name LIKE :nombre"),
	@NamedQuery(name="byEmail", query="SELECT u FROM User u WHERE u.password LIKE :pass")	
})
public class User implements Serializable, IUser, Prototype<User>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")	
	private Integer id;
	@Column(name="NAME", unique=true)	
	private String name;
	@Column(name="EMAIL", unique=true)	
	private String email;
	@Column(name="PASSWORD")	
	private String password;
	
	@JoinColumn(name="LISTAGRUPOS")
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_GRUPO",
			joinColumns=@JoinColumn(name="ID_USER", table="USERS", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="ID_GRUPO", table="GRUPOS", referencedColumnName="ID"))

	private List<Grupo> listaGrupos;
	
	
	
	@Override
	public User clone(){
		User user = new User();
		user.setEmail(this.getEmail());
		user.setId(this.getId());
		user.setName(this.getName());
		user.setPassword(this.getPassword());
		user.setListaGrupos(this.getListaGrupos());
		
		return user;
	}
	
	
	
	
	public User() {
		super();
	}
	
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	


	public User(Integer id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}


	@Override
	public Integer getId() {
		return id;
	}


	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}


	@Override
	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	
	public void addGrupo(Grupo grupo){
		this.listaGrupos.add(grupo);
	}
	
	public void removeGrupo(Grupo grupo){
		this.listaGrupos.remove(grupo);
	}
	
	
	
}
