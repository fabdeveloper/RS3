package src.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import src.inter.IPedido;

@Entity
@Table(name="PEDIDOS")
public class Pedido implements Serializable, IPedido{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="IDCLIENT")
	private Integer idClient;

//	@Column(name="LISTPRODS")
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval= true)
	@JoinColumn(name="pedido_id")
	private List<? extends Articulo> listProds;
	
	@Column(name="VALOR")
	private Double valor;

	@Column(name="ESTADOPAGO")
	private String estadoPago;

	@Column(name="TIPOENVIO")
	private String tipoEnvio;
	
	@Column(name="LUGARENTREGA")
	private String lugarEntrega;

	
	
	/************************************************/
	
	
	
	
	@Override
	public IPedido clone(){
		
		Pedido nuevoClon = new Pedido();
//		nuevoClon.setId(this.getId());
		nuevoClon.setIdClient(this.getIdClient());
		nuevoClon.setEstadoPago(this.getEstadoPago());
		nuevoClon.setListProds(this.getListProds());
		nuevoClon.setLugarEntrega(this.getLugarEntrega());
		nuevoClon.setTipoEnvio(this.getTipoEnvio());
		nuevoClon.setValor(this.getValor());		
		
		
		return nuevoClon;
		
	}
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	@Override
	public Double getValor() {
		return valor;
	}


	@Override
	public String getEstadoPago() {
		return estadoPago;
	}


	@Override
	public String getTipoEnvio() {
		return tipoEnvio;
	}



	@Override
	public String getLugarEntrega() {
		return lugarEntrega;
	}
	@Override
	public List<? extends Articulo> getListProds() {
		return listProds;
	}
	public void setListProds(List<? extends Articulo> listProds) {
		this.listProds = listProds;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public void setLugarEntrega(String lugarEntrega) {
		this.lugarEntrega = lugarEntrega;
	}


	



}
