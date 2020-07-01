package src.inter;

import java.util.List;

public interface Catalogo<P> {
	
	public void loadCatalogo(String query);
	public List<P> getProducts();
	public void selectProduct(P prod);
	public P getSel();
	public Integer countProducts();

}
