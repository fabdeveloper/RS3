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
import src.impl.DispoImpl;
import src.inter.Caja;
import src.inter.ICarrito;
import src.inter.Catalogo;
import src.inter.IDispo;
import src.inter.IGestorE;
import src.inter.IPedido;
import src.inter.IShop;
import src.inter.IUser;
import src.inter.Shopper;

@SessionScoped
public class ShopFacade implements IShop, Serializable{
	
	@Inject
	private DispoImpl dispo;
	@Inject
	private CarritoOfertas carrito;
	@Inject
	private PedidoGestor gestorPedidos;
	
	private Pedido pedido;
	private User user;
	
	

	@Override
	public ICarrito<Oferta> getCarrito() {
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
	public Caja getCaja() {
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
		pedido.setValor(valora(carrito.getProducts()));
		pedido.setEstadoPago("PENDIENTE");
		pedido.setTipoEnvio("NORMAL");
		pedido.setLugarEntrega("ALMACEN");
		
		getGestorPedidos().getDao().create(pedido);
		
		setPedido(pedido);		
		
	}
	
	private Float valora(List<Oferta> lista){
		Float retorno = 0f;
		for(Oferta oferta: lista){
			retorno += oferta.getPrecio();
		}
		return retorno;
	}
	@Override
	public IDispo getDispoManager() {
		return dispo;
	}
	@Override
	public List<Product> getListaProductos() {		
		return dispo.getListaProductos();		
	}
	@Override
	public List<Articulo> getListaArticulos() {
		return dispo.getListaArticulos();
	}	
	@Override
	public List<Oferta> getListaOfertas() {
//		System.out.println("ShopFacade.verOfertas()  *** "  + new Date());		

		return dispo.getListaOfertas();		

//		listaOfertas.forEach(e -> System.out.println( "oferta id = " + e.getId() + ", descripcion = " + e.getDescripcion()));
						
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



}
