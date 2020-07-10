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
import javax.persistence.JoinTable;
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

//	@OneToMany(cascade= CascadeType.ALL, orphanRemoval= true)
//	@JoinColumn(name="pedido_id")
	
	
	@JoinColumn(name="LISTPRODS")
	@ManyToMany
	@JoinTable(name="PEDIDO_ARTICULO",
			joinColumns=@JoinColumn(name="id_Pedido", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="id_Articulo", referencedColumnName="id")	)

	private List<? extends Oferta> listaProds;
	
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
	public List<? extends Oferta> getListProds() {
		return listaProds;
	}
	public void setListProds(List<? extends Oferta> list) {
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
