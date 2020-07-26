package src.inter;

import java.util.List;

public interface IShop {
	
	public IDispo getDispoManager();
	public ICarrito getCarrito();
	public IValoracionStrategy getValorationStrategy();
	public IGestorE getGestorPedidos();
	public IPedido getPedido();
	public IUser getUser();
	public ICajaStrategy getCaja();
	
	
	/*************************/
	
	
	public List getListaProductos();
	public List getListaArticulos();
	public List getListaOfertas();
	public void seleccionarArticulo(Integer idArticulo);
	public void seleccionarProducto(Integer idProducto);
	public void seleccionarOferta(Integer idOferta);
	public void crearPedido();

	
	

}
