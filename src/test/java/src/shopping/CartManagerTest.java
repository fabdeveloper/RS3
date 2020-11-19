package src.shopping;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.entity.Cart;
import src.entity.Oferta;
import src.factory.CartFactory;
import src.factory.IBeanFactory;
import src.shopping.impl.CartManager;
import src.shopping.impl.ValuationManager;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IValuationManager;
import static org.mockito.Mockito.*;

public class CartManagerTest {
	
	private ICartManager cartManager;
	
	@Before
	public void initDependencies(){
		cartManager = new CartManager();		
		((CartManager)cartManager).setFactory(mockFactory());
		((CartManager)cartManager).setValuationManager(mockValuationManager());
	}
	
	@Test
	public void addItemTest(){		
		Oferta oferta = new Oferta();
		Cart cart = cartManager.addItem(oferta);
		int numItemsAntes = cart.getListaOfertas().size();
		cart = cartManager.addItem(oferta);
		int numItemsDespues = cart.getListaOfertas().size();		
		
		assertTrue("error", numItemsDespues-numItemsAntes == 1);
	}
	
	@Test
	public void removeItemTest(){			
		Oferta oferta1 = new Oferta();
		oferta1.setId(1);
		Oferta oferta2 = new Oferta();
		oferta2.setId(2);

		cartManager.addItem(oferta1);
		Cart cart = cartManager.addItem(oferta2);
		int numItemsAntes = cart.getListaOfertas().size();
		cart = cartManager.removeItem(oferta2);
		int numItemsDespues = cart.getListaOfertas().size();			
	
		assertTrue("error", numItemsDespues == numItemsAntes-1);
	}
	
	@Test
	public void resetTest(){		
		Cart cart = cartManager.reset();
		
		assertTrue("cart = null", cart != null);
		assertTrue("no es un Cart", cart instanceof Cart);
	}
	
	
	
	
	private IBeanFactory<Cart> mockFactory(){
		IBeanFactory<Cart> sl = mock(CartFactory.class);
		if(sl != null)
		when(sl.crear()).thenReturn(new Cart());
		return sl;
	}
	
	private IValuationManager mockValuationManager(){
		IValuationManager val = mock(ValuationManager.class);
		when(val.valuate()).thenReturn(0f);
		return val;
	}
}
