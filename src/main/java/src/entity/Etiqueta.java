/**
 * 
 */
package src.entity;

import java.io.Serializable;
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

/**
 * @author fabo_
 *
 */
@RequestScoped
@Entity
@Table(name="ETIQUETAS")
@NamedQueries({

	@NamedQuery(name="etiquetasAll", query="SELECT b FROM Etiqueta b ORDER BY b.id DESC"),
	@NamedQuery(name="etiquetasActivasHoy", query="SELECT b FROM Etiqueta b WHERE b.startDate < CURRENT_DATE AND CURRENT_DATE < b.stopDate")



	})
public class Etiqueta implements Serializable, IPrototype<Etiqueta>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;

	@Column(name="START_DATE")
	private Date startDate;	
	
	@Column(name="STOP_DATE")
	private Date stopDate;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;
	
	
	
	
	
	
	@Override
	public Etiqueta clone(){
		Etiqueta nuevo = new Etiqueta();
		nuevo.setId(this.getId());
		nuevo.setName(this.getName());
		nuevo.setStartDate(this.getStartDate());
		nuevo.setStopDate(this.getStopDate());
		nuevo.setCreationDate(this.getCreationDate());
		nuevo.setExpirationDate(this.getExpirationDate());
		

		return nuevo;
	}
	
	
		
	
	
	/**********************************************************************************/

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





	public Date getStartDate() {
		return startDate;
	}





	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}





	public Date getStopDate() {
		return stopDate;
	}





	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
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
	
	

	
	
	
	
	

}
