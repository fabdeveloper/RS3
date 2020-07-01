package src.service;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ServiceTest {
//	private Service componente;
	

	
	
	
	@Test
	public void getMethodTest(){
		
		Service componente = new Service();
		String  esperado = " getMethod ";
		String recibido = componente.getMethod();
		boolean resultado = esperado.matches(recibido);
		
		assertTrue(resultado);
		
	}
	
	@Test
	public void setMethodTest(){
		
		Service componente = new Service();
		String  esperado = " setMethod ";
		String recibido = componente.setMethod();
		boolean resultado = esperado.matches(recibido);
		
		assertTrue(resultado);
	}
	
	@Test
	public void newMethodTest(){
		
		Service componente = new Service();
		String  esperado = " newMethod ";
		String recibido = componente.newMethod();
		boolean resultado = esperado.matches(recibido);
		
		assertTrue(resultado);
	}
	
	@Test
	public void cancelMethodTest(){
		Service componente = new Service();
		String  esperado = " cancelMethod ";
		String recibido = componente.cancelMethod();
		boolean resultado = esperado.matches(recibido);
		
		assertTrue(resultado);
		
	}
	
	

}
