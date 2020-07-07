package src.inter;

public interface Shopper<OFER, ARTI, PEDIDO> {
	
	public Catalogo<OFER> getCatalogo();
	public ICarrito<ARTI> getCarrito();
	public IGestorE<PEDIDO> getGestorPedidos();
	public IPedido getPedido();
	public IUser getUser();
	public Caja getCaja();
	
	
	/*************************/
	
	
	
	public void verOfertas();
	public void seleccionarArticulo();
	public void crearPedido();




}
