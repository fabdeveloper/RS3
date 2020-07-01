package src.wizzard;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Libro;
import src.entity.Pedido;
import src.entity.User;
import src.inter.Caja;
import src.inter.Carrito;
import src.inter.Catalogo;
import src.inter.Gestor;
import src.inter.IPedido;
import src.inter.IUser;
import src.inter.Shopper;
import src.beans.PedidoFacade;



@SessionScoped
public class LibroShopper implements /*Shopper<Libro,Articulo, Pedido>,*/ Serializable{
	
	
	
	@Inject
	private CatalogoLibros catalogo;
	@Inject
	private CarritoLibros carrito;
	private PedidoGestor gestor;
	private IPedido pedido;
//	private User user;
//	private Caja caja;
	
	
	
	

//	@Override
	public Catalogo<Libro> getCatalogo() {
		return catalogo;
	}

	//	@Override
	public Carrito<Libro> getCarrito() {
		return carrito;
	}
	
	//	@Override
	public Gestor<Pedido> getGestor() {
		return gestor;
	}

	//	@Override
	public IPedido getPedido() {
		return pedido;
	}

	//	@Override
	public IUser getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	//	@Override
	public Caja getCaja() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/***********************************************************/


	public void setCatalogo(CatalogoLibros catalogo) {
		this.catalogo = catalogo;
	}

	public void setCarrito(CarritoLibros carrito) {
		this.carrito = carrito;
	}

	public void setGestor(PedidoGestor gestor) {
		this.gestor = gestor;
	}

	public void setPedido(IPedido pedido) {
		this.pedido = pedido;
	}

	//	@Override
	public Gestor<Pedido> getGestorPedidos() {
		// TODO Auto-generated method stub
		return null;
	}


	
	


	
	
	
	
	

}
