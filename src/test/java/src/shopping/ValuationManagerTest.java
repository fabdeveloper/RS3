package src.shopping;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import src.entity.CartItem;
import src.entity.Oferta;
import src.shopping.impl.ValuationManager;
import src.shopping.inter.IValuationManager;

public class ValuationManagerTest {
	
	private IValuationManager valuationManager;
	private List<CartItem> listaItems;
	
	@Before
	public void initDependencies(){
		valuationManager = new ValuationManager();	
		initListaItems();
	}
	
	private void initListaItems() {
		listaItems = new ArrayList<CartItem>();
		
		CartItem item1 = new CartItem();
		Oferta oferta1 = new Oferta();
		oferta1.setPrecio(10f);
		oferta1.setId(1);
		item1.setOferta(oferta1);
		item1.setCounter(1);
		item1.setId(1);
		
		CartItem item2 = new CartItem();
		Oferta oferta2 = new Oferta();
		oferta2.setPrecio(20f);
		oferta2.setId(2);
		item2.setOferta(oferta2);
		item2.setCounter(2);
		item2.setId(2);
		
		listaItems.add(item1);
		listaItems.add(item2);
	}
	
	@Test
	public void valuateTest(){	
		
		Float valorItem1 = 10f;
		Float valorItem2 = 40f;
		
		Float valoracion_total = valorItem1 + valorItem2;
		System.out.println("Valoracon esperada = " + valoracion_total);
		
		valuationManager.setListaItems(listaItems);
		
		Float total = valuationManager.valuate();
		System.out.println("Valoracon obtenida = " + total);



		
		assertTrue("error valorando", total.compareTo(valoracion_total) == 0);
	}

}
