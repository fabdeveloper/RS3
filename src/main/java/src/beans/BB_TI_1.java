package src.beans;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.dao.UserDao;
import src.entity.Libro;
import src.entity.Pedido;
import src.entity.User;
import src.factory.FactoryImpl;
import src.wizzard.LibroShopper;


@Named("bB_TI_1")
@SessionScoped
public class BB_TI_1 implements Serializable{
	
	@Inject FactoryImpl factory;
	@Inject UserFacade userFacade;
	@Inject LibroFacade libroFacade;

	@Inject LibroShopper shopper;
	
	private Integer indiceSeleccionado = 0;
	
	
	
	
	
	
	
	
	public void crearUser(){		
		System.out.println("BB_TI_1.crearUser() ...  " + new Date());	
		
		User user = (User)factory.crear("USER");	
		userFacade.create(user);
		
		System.out.println("BB_TI_1.crearUser() : CREADO - " + user + " -  user ID = " + user.getId() + "  - ****  " + new Date());	
		System.out.println("BB_TI_1.crearUser() : Numero de usuarios =  " + userFacade.count() + "  - ****  " + new Date());	

	}
	
	public void crearLibro(){
		System.out.println("BB_TI_1.crearLibro() ...  " + new Date());	
		
		Libro libro = (Libro)factory.crear("LIBRO");	
		libroFacade.create(libro);
		
		System.out.println("BB_TI_1.crearLibro() : CREADO - " + libro + " -  user ID = " + libro.getId() + "  - ****  " + new Date());	
		System.out.println("BB_TI_1.crearLibro() : Numero de libros =  " + libroFacade.count() + "  - ****  " + new Date());	

	}
	
	public void verCatalogo(){		
		System.out.println("BB_TI_1.verCatalogo() ...  " + new Date());		
		
		shopper.getCatalogo().loadCatalogo("TODO");
		
		System.out.println("items en catalogo = " + shopper.getCatalogo().getProducts() + "  **********  " + new Date());		

		System.out.println("items en BD = " + shopper.getCatalogo().countProducts() + "  **********  " + new Date());		

	}
	
	public void seleccionarProducto(){		
		System.out.println("BB_TI_1.seleccionarProducto() ...  " + new Date());	
		shopper.getCatalogo().selectProduct(shopper.getCatalogo().getProducts().get(indiceSeleccionado));
		
		shopper.getCarrito().add(shopper.getCatalogo().getSel());
		System.out.println("BB_TI_1.seleccionarProducto() - Carrito.add =  " + shopper.getCarrito().getProducts() + " - " + new Date());	

	}
	
	public void comprar(){		
//		System.out.println("BB_TI_1.comprar() ...  " + new Date());	
//		
//		Pedido pedido = (Pedido)shopper.getGestor().getFactory().crear("PEDIDO");
//		
//		pedido.setListProds(shopper.getCarrito().getProducts());
//		
//		shopper.setPedido(pedido);
	}

	public Integer getIndiceSeleccionado() {
		return indiceSeleccionado;
	}

	public void setIndiceSeleccionado(Integer indiceSeleccionado) {
		this.indiceSeleccionado = indiceSeleccionado;
	}
	
	/******************************************/
	
	

}
