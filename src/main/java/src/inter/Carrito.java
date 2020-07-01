package src.inter;

import java.util.List;

public interface Carrito<P> {
	
	public void add(P prod);
	public void remove(P prod);
	public void removeAll();

	public void valuate(String strategy);
	public List<P> getProducts();
	
	

}
