package src.inter;

public interface IDispo {	
	
	public void listaProductos();
	public void listaArticulos();
	public void listaOfertas();
	public void seleccionarArticulo(Integer articulo_id);
	public void seleccionarProducto(Integer producto_id);
	public void init(Integer producto_id, Integer articulo_id);

}
