package src.wizzard;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Pedido;
import src.entity.Product;
import src.entity.User;
import src.gestor.IGestorE;
import src.gestor.PedidoGestor;
import src.impl.DispoImpl;
import src.inter.ICajaStrategy;
import src.inter.ICarrito;
import src.inter.Catalogo;
import src.inter.IDispo;
import src.inter.IPedido;
import src.inter.IShop;
import src.inter.IUser;
import src.inter.IValoracionStrategy;

@SessionScoped
public class ShopFacade implements IShop, Serializable{
	
	@Inject	private DispoImpl dispo;
	@Inject	private CarritoOfertas carrito;
	@Inject	private PedidoGestor gestorPedidos;
	@Inject private ValorationStrategyImpl valorationStrategy;
	
	private Pedido pedido;
	private User user;
	
	

	@Override
	public ICarrito<Oferta, ValorationStrategyImpl> getCarrito() {
		return carrito;
	}
	@Override
	public IGestorE<Pedido> getGestorPedidos() {
		return gestorPedidos;
	}
	@Override
	public IPedido getPedido() {
		return pedido;
	}
	@Override
	public IUser getUser() {
		return user;
	}
	@Override
	public ICajaStrategy getCaja() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void crearPedido() {
		Pedido pedido = getGestorPedidos().getFactory().crear();
		
		pedido.setListProds(carrito.getProducts());
		pedido.setIdClient(user.getId());
		pedido.setValor(valorationStrategy.valora(carrito.getProducts()));
		pedido.setEstadoPago("PENDIENTE");
		pedido.setTipoEnvio("NORMAL");
		pedido.setLugarEntrega("ALMACEN");
		
		getGestorPedidos().getDao().create(pedido);
		
		setPedido(pedido);		
		
	}

	@Override
	public IDispo getDispoManager() {
		return dispo;
	}
	@Override
	public List<Product> getListaProductos() {	
		dispo.refreshListaProductos();
		return dispo.getListaProductos();		
	}
	@Override
	public List<Articulo> getListaArticulos() {
		dispo.refreshListaArticulos();
		return dispo.getListaArticulos();
	}	
	@Override
	public List<Oferta> getListaOfertas() {
		dispo.refreshListaOfertas();
		return dispo.getListaOfertas();							
	}
	@Override
	public void seleccionarArticulo(Integer idArticulo) {
		dispo.setArticuloSeleccionado(idArticulo);		
	}	
	@Override
	public void seleccionarProducto(Integer idProducto) {
		dispo.setProductoSeleccionado(idProducto);		
	}	
	@Override
	public void seleccionarOferta(Integer idOferta) {
		Oferta of = null;
		List<Oferta> listaOfertas = dispo.getListaOfertas();
		for(Oferta oferta : listaOfertas){
			if(oferta.getId() == idOferta)of = oferta;
		}
		if(of != null)
		carrito.add(of);		
	}
	
	
	
	/**********************************/
	
	public void refreshListaProductos(){
		dispo.refreshListaProductos();
	}
	public void refreshListaArticulos(){
		dispo.refreshListaArticulos();
	}
	public void refreshListaOfertas(){
		dispo.refreshListaOfertas();
	}
	@Override
	public IValoracionStrategy getValorationStrategy() {
		return valorationStrategy;
	}



}
