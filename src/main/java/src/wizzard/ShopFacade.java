package src.wizzard;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Pedido;
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

//@Stateful
@SessionScoped
public class ShopFacade implements IShop, Serializable{
	
//	@Inject
//	private CatalogoOfertas catalogo;
	@Inject
	private DispoImpl dispo;
	@Inject
	private CarritoArticulo carrito;
	@Inject
	private PedidoGestor gestorPedidos;
	
	private Pedido pedido;
	private User user;
	
	

	@Override
	public ICarrito<Articulo> getCarrito() {
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public IDispo getDispoManager() {
		return dispo;
	}
	@Override
	public void verProductos() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void verArticulos() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void verOfertas() {
		System.out.println("ShopFacade.verOfertas()  *** "  + new Date());		

		List<Oferta> listaOfertas;
		
		
//		getCatalogo().loadCatalogo("TODO");
//		listaOfertas = getCatalogo().getProducts();
		listaOfertas.forEach(e -> System.out.println( "oferta id = " + e.getId() + ", descripcion = " + e.getDescripcion()));
				
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
		// TODO Auto-generated method stub
		
	}
	




}
