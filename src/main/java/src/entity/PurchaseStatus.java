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

import src.inter.IPrototype;

@RequestScoped
@Entity
@Table(name="PURCHASE_STATUS")
public class PurchaseStatus implements Serializable, IPrototype<PurchaseStatus>{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="REMARKS")
	private String remark;
	
//	@OneToOne
//	private Order order;
	
	@Column(name="MODIFICATION_DATE")
	private Date lastModification;
	
//	@ManyToOne
//	private PurchaseStatusType purchaseStatusType;
	
	public PurchaseStatus clone(){
		PurchaseStatus nuevo = new PurchaseStatus();
		nuevo.setId(this.getId());
		nuevo.setLastModification(this.getLastModification());
//		nuevo.setOrder(this.getOrder());
		nuevo.setRemark(this.getRemark());
		
		
		return nuevo;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
