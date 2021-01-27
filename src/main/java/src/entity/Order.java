package src.entity;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="ORDERS")
public class Order implements Serializable, IPrototype<Order>{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@NotNull
	@ManyToOne(optional=false)
	private User client;
	
	@NotNull
	@OneToOne(optional=false, cascade={CascadeType.ALL})
	private Cart cart;
	
	@Column(name="CONFIRMATION_DATE")
	private Date confirmationDate;
	
	@NotNull
	@Column(name="LAST_MODIFICATION_DATE")
	private Date lastModificationDate;
	
	@NotNull
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	//  REEMPLAZAR POR UN STRING ************************************
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	private PurchaseStatus purchaseStatus;
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	private DeliveryDetails deliveryDetails;
	
	
	public Order clone(){
		Order nuevo = new Order();
		nuevo.setCart(this.getCart());
		nuevo.setClient(this.getClient());
		nuevo.setConfirmationDate(this.getConfirmationDate());
		nuevo.setLastModificationDate(this.getLastModificationDate());
		nuevo.setCreationDate(this.getCreationDate());
		nuevo.setDeliveryDetails(this.getDeliveryDetails());
		nuevo.setId(this.getId());
		nuevo.setPurchaseStatus(this.getPurchaseStatus());
		
		return nuevo;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public DeliveryDetails getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
