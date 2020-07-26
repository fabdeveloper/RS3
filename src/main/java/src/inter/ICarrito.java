package src.inter;

import java.util.List;

public interface ICarrito<P, VS> {
	
	public void add(P prod);
	public void remove(P prod);
	public void removeAll();

	public Float valuate(VS strategy);
	public List<P> getProducts();
	
	

}
