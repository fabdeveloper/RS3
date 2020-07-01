package src.wizzard;

import java.util.ArrayList;
import java.util.List;

import src.entity.*;
import src.inter.Carrito;

public class CarritoGeneral implements Carrito<Product>{
	
	private List<Product> products = new ArrayList<Product>();
	
	
	@Override
	public void add(Product prod) {
		products.add(prod);
	}

	@Override
	public void remove(Product prod) {
		products.remove(prod);		
	}

	@Override
	public void removeAll() {
		products.clear();
	}

	@Override
	public void valuate(String strategy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

}
