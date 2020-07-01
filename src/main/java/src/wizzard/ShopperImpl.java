package src.wizzard;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Pedido;
import src.entity.User;
import src.inter.Caja;
import src.inter.Carrito;
import src.inter.Catalogo;
import src.inter.Gestor;
import src.inter.IPedido;
import src.inter.IUser;
import src.inter.Shopper;

public class ShopperImpl implements Shopper<Oferta, Articulo, Pedido>{
	
	private CatalogoOfertas catalogo;
	private CarritoArticulo carrito;
	private PedidoGestor gestorPedidos;
	
	private Pedido pedido;
	private User user;
	
	
	@Override
	public Catalogo<Oferta> getCatalogo() {
		return catalogo;
	}
	@Override
	public Carrito<Articulo> getCarrito() {
		return carrito;
	}
	@Override
	public Gestor<Pedido> getGestorPedidos() {
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



}
