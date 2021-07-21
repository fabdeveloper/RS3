package src.shopping;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.inter.IServiceLocator;
import src.shopping.inter.ICartManager;
import src.shopping.inter.IPurchaseManager;
import src.shopping.inter.IShoppingFacade;

public class PurchaseManagerTest {
	
	private IPurchaseManager purchaseManager;
	private IServiceLocator serviceLocator;
	private IShoppingFacade shoppingFacade;
	private ICartManager cartManager;
	
	
	
	@Before
	public void initDependencies(){
		
	}
	
	
	
	
	@Test
	public void cancelOrderTest(){		
		
		assertTrue("error", true);
	}
	
	@Test
	public void isCancelableTest(){		
		
		assertTrue("error", true);
	}
	
	@Test
	public void preConfirmationTest(){		
		
		assertTrue("error", true);
	}
	
	@Test
	public void paymentErrorTest(){		
		
		assertTrue("error", true);
	}
	
	@Test
	public void updateOrderTest(){		
		
		assertTrue("error", true);
	}
	
	
	

}
