package src.inter;

import java.util.List;

public interface IDispo {	
	
	public List<? extends Object> getListaProductos();
	public List<? extends Object> getListaArticulos();
	public List<? extends Object> getListaOfertas();
	public void setArticuloSeleccionado(Integer articulo_id);
	public void setProductoSeleccionado(Integer producto_id);
	public boolean isArticuloOk();
	public boolean isProductOk();
	public void init(Integer producto_id, Integer articulo_id);
	
	
	
	public void refreshListaProductos();	
	public void refreshListaArticulos();	
	public void refreshListaOfertas();

}
