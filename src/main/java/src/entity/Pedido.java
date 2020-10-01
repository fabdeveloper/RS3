package src.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import src.inter.IPedido;
import src.inter.Prototype;

@RequestScoped
@Entity
@Table(name="PEDIDOS")
@Embeddable
public class Pedido implements Serializable, IPedido, Prototype<Pedido>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="IDCLIENT")
	private Integer idClient;
	
	@JoinColumn(name="LISTAPRODS")
	@ManyToMany
	@JoinTable(name="PEDIDO_OFERTAS",
			joinColumns=@JoinColumn(name="id_Pedido", table="PEDIDOS", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="id_Oferta", table="OFERTAS",  referencedColumnName="id"))	
	private List<Oferta> listaProds;
	
	@Column(name="VALOR")
	private Float valor;

	@Column(name="ESTADOPAGO")
	private String estadoPago;

	@Column(name="TIPOENVIO")
	private String tipoEnvio;
	
	@Column(name="LUGARENTREGA")
	private String lugarEntrega;

	
	
	/************************************************/
	
	
	
	
	@Override
	public Pedido clone(){
		
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
	public Float getValor() {
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
	public List<Oferta> getListProds() {
		return listaProds;
	}
	public void setListProds(List<Oferta> list) {
		this.listaProds = list;
	}
	public void setValor(Float valor) {
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
