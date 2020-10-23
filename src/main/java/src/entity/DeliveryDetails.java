package src.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import src.inter.Prototype;


@Entity
@Table(name="DELIVERY_DETAILS")
public class DeliveryDetails implements Serializable, Prototype<DeliveryDetails>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DELIVERY_ADDRESS")
	private String deliveryAddress;
	
//	@ManyToOne
//	private DeliveryType deliveryType;
	
	@Column(name="REMARKS")
	private String remark;
	
	@OneToOne
	@JoinColumn(name="Id")
	private Order order;
	
	public DeliveryDetails clone(){
		DeliveryDetails nuevo = new DeliveryDetails();
		nuevo.setDeliveryAddress(this.getDeliveryAddress());
		nuevo.setId(this.getId());
		nuevo.setOrder(this.getOrder());
		nuevo.setRemark(this.getRemark());		
		
		
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
