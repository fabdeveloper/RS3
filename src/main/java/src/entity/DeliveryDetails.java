package src.entity;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="DELIVERY_DETAILS")
public class DeliveryDetails implements Serializable, IPrototype<DeliveryDetails>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@NotNull
	@Column(name="DELIVERY_ADDRESS")
	private String deliveryAddress;
	
//	@ManyToOne
//	private DeliveryType deliveryType;
	
	@Column(name="REMARKS")
	private String remark;
	
	@NotNull
	@Column(name="STATUS")
	private String status;
	
	@NotNull
	@Column(name="LAST_MODIFICATION_DATE")
	private Date lastModificationDate;
	
//	@ManyToOne
	@Column(name="DELIVERY_TYPE")
	private String deliveryType;
	
//	@OneToOne
//	private Order order;
	
	public DeliveryDetails clone(){
		DeliveryDetails nuevo = new DeliveryDetails();
		nuevo.setDeliveryAddress(this.getDeliveryAddress());
		nuevo.setId(this.getId());
//		nuevo.setOrder(this.getOrder());
		nuevo.setRemark(this.getRemark());	
		nuevo.setStatus(this.getStatus());
		nuevo.setLastModificationDate(this.getLastModificationDate());
		nuevo.setDeliveryType(this.getDeliveryType());		
		
		return nuevo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
