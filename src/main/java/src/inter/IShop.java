package src.inter;

public interface IShop {
	
	public IDispo getDispoManager();
	public ICarrito<? extends Object> getCarrito();
	public IGestorE<? extends Object> getGestorPedidos();
	public IPedido getPedido();
	public IUser getUser();
	public Caja getCaja();
	
	
	/*************************/
	
	
	public void verProductos();
	public void verArticulos();
	public void verOfertas();
	public void seleccionarArticulo(Integer idArticulo);
	public void seleccionarProducto(Integer idProducto);
	public void seleccionarOferta(Integer idOferta);
	public void crearPedido();

	
	

}
