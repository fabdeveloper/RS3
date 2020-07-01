package src.factory;




import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import src.entity.Libro;
import src.entity.Pedido;
import src.entity.User;

public class FactoryImplTest {
	
	private FactoryImpl factoryImpl = new FactoryImpl();
	
	@Before
	public void doBefore(){
		
	}
	
//	@Test
	public void crearLibroTest(){
		
		Object libro = null;
		libro = factoryImpl.crear("LIBRO");
//		assert (libro  instanceof Libro) : "no es un libro"; 
		
		Assert.assertTrue("no es un Libro", libro  instanceof Libro);
		
		System.out.println("objeto = " + libro);		
	}
	
//	@Test
	public void crearUserTest(){
		
		Object user = null;
		user = factoryImpl.crear("USER");
		
		Assert.assertTrue("no es un User", user  instanceof User);
		
		System.out.println("objeto = " + user);		
	}
	
//	@Test
	public void crearPedidoTest(){
		
		Object pedido = null;
		pedido = factoryImpl.crear("PEDIDO");
		
		Assert.assertTrue("no es un Pedido", pedido  instanceof Pedido);
		
		System.out.println("objeto = " + pedido);		
	}

}
