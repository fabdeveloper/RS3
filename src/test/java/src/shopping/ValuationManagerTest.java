package src.shopping;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import src.entity.Oferta;
import src.shopping.impl.ValuationManager;
import src.shopping.inter.IValuationManager;

public class ValuationManagerTest {
	
	private IValuationManager valuationManager;
	
	@Before
	public void initDependencies(){
		valuationManager = new ValuationManager();		
	}
	
	@Test
	public void valuateTest(){				
//		Oferta oferta = new Oferta();
//		oferta.setPrecio(10f);
//		List<Oferta> lista = new ArrayList<Oferta>();
//		for (int i = 0; i < 10; i++) {
//			lista.add(oferta);
//		}		
//		float esperado = 100f;
//		valuationManager.setListaItems(lista);
//		float total = valuationManager.valuate();	
//		
//		assertTrue("error", total == esperado);
	}

}
