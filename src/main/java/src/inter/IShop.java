package src.inter;

import java.util.List;

public interface IShop {
	
	public IDispo getDispoManager();
	public ICarrito<? extends Object> getCarrito();
	public IGestorE<? extends Object> getGestorPedidos();
	public IPedido getPedido();
	public IUser getUser();
	public Caja getCaja();
	
	
	/*************************/
	
	
	public List<? extends Object> getListaProductos();
	public List<? extends Object> getListaArticulos();
	public List<? extends Object> getListaOfertas();
	public void seleccionarArticulo(Integer idArticulo);
	public void seleccionarProducto(Integer idProducto);
	public void seleccionarOferta(Integer idOferta);
	public void crearPedido();

	
	

}
