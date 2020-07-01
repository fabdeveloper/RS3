package src.inter;

public interface Shopper<OFER, ARTI, PEDIDO> {
	
	public Catalogo<OFER> getCatalogo();
	public Carrito<ARTI> getCarrito();
	public Gestor<PEDIDO> getGestorPedidos();
	public IPedido getPedido();
	public IUser getUser();
	public Caja getCaja();




}
