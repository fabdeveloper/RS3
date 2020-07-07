package src.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.dao.LibroDao;
import src.dao.OfertaDao;
import src.dao.PedidoDao;
import src.dao.UserDao;
import src.entity.Articulo;
import src.entity.Libro;
import src.entity.Oferta;
import src.entity.Pedido;
import src.entity.User;
import src.factory.FactoryImpl;
import src.inter.ICarrito;
import src.inter.Catalogo;
import src.wizzard.CarritoArticulo;
import src.wizzard.ShopFacade;


@Named
@SessionScoped
public class BB_TI_2 implements Serializable{
	
	@Inject
	private FactoryImpl factory;
	@Inject
	private LibroDao libroDao;
	@Inject
	private OfertaDao ofertaDao;
	@Inject
	private UserDao userDao;
	@Inject
	private PedidoDao pedidoDao;
	@Inject
	private ShopFacade shop;
	
	private Integer numOferta = 0;
	
	
	public Integer getNumOferta() {
		return numOferta;
	}

	public void setNumOferta(Integer numOferta) {
		this.numOferta = numOferta;
	}

	public void crearProd(){
		
		System.out.println("BB_TI_2.crearProd() ...  " + new Date());	

		
		Libro libro = (Libro)factory.crear("LIBRO");
		
		libro.setAuthor("J Verne");
		libro.setTitle("La isla misteriosa");
		libroDao.create(libro);
		
		System.out.println("BB_TI_2.crearProd()- CREADO LIBRO : id = " + libro.getId() + ", autor = " + libro.getAuthor()  + ", titulo = " + libro.getTitle() + "  * "  + new Date());	

		
	}
	
	public void crearOfertas(){
		
		System.out.println("BB_TI_2.crearOfertas() ...  " + new Date());			
		
		Oferta oferta1 = (Oferta)factory.crear("OFERTA");
		Oferta oferta2 = (Oferta)factory.crear("OFERTA");
		Oferta oferta3 = (Oferta)factory.crear("OFERTA");
		
		oferta1.setDescripcion("Oferta 1");
		oferta1.setPrecio(1f);
		oferta1.setProduct_id(8);		
		ofertaDao.create(oferta1);
		
		oferta2.setDescripcion("Oferta 2");
		oferta2.setPrecio(2f);
		oferta2.setProduct_id(8);		
		ofertaDao.create(oferta2);
		
		oferta3.setDescripcion("Oferta 3");
		oferta3.setPrecio(3f);
		oferta3.setProduct_id(8);		
		ofertaDao.create(oferta3);
		
		System.out.println("BB_TI_2.crearOfertas() - CREADAS :  " + oferta1 + ", " + oferta2 + ", " + oferta3 + " ***  "    + new Date());	
		System.out.println("-> :  id= " + oferta1.getId() + ", descrition= " + oferta1.getDescripcion() + ", precio= " + oferta1.getPrecio()+ ", prod= " +  oferta1.getProduct_id()  + " ***  "    + new Date());	
		System.out.println("-> :  id= " + oferta2.getId() + ", descrition= " + oferta2.getDescripcion() + ", precio= " + oferta2.getPrecio()+ ", prod= " +  oferta2.getProduct_id()  + " ***  "    + new Date());	
		System.out.println("-> :  id= " + oferta3.getId() + ", descrition= " + oferta3.getDescripcion() + ", precio= " + oferta3.getPrecio()+ ", prod= " +  oferta3.getProduct_id()  + " ***  "    + new Date());	

	}
	
	public void crearUser(){
		
		System.out.println("BB_TI_2.crearUser()  *** "  + new Date());		
		User user = (User)factory.crear("USER");
		user.setName("Donald");
		
		userDao.create(user);		
		System.out.println("-> :  id= " + user.getId() + ", nombre= " + user.getName() + "  *** " + new Date());	

	}
	
	public void verCatalogo(){
		System.out.println("BB_TI_2.verCatalogo()  *** "  + new Date());		

		List<Oferta> listaOfertas;
		
		shop.getCatalogo().loadCatalogo("TODO");
		listaOfertas = shop.getCatalogo().getProducts();
		listaOfertas.forEach(e -> System.out.println( "oferta id = " + e.getId() + ", descripcion = " + e.getDescripcion()));
		
	}
	
	public void seleccionarOferta(){
		System.out.println("BB_TI_2.seleccionarOfertas()  *** "  + new Date());		

		ICarrito carrito = shop.getCarrito();
		Catalogo catalogo = shop.getCatalogo();
		catalogo.loadCatalogo("TODO");
		catalogo.selectProduct(catalogo.getProducts().get(numOferta));
		
		Oferta ofertaSeleccionada = (Oferta)catalogo.getSel();
		
		Articulo articulo = (Articulo)factory.crear("ARTICULO");
		articulo.setDescripcion(ofertaSeleccionada.getDescripcion());
		articulo.setPrice(ofertaSeleccionada.getPrecio());
		articulo.setProduct_id(ofertaSeleccionada.getProduct_id());
		
		carrito.add(articulo);
		
		List<Articulo> listaOfertas = carrito.getProducts();
		listaOfertas.forEach(e -> System.out.println("articulo id = " + e.getId() + ", description = " + e.getDescripcion()));
	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Transactional
	public void crearPedido(){
		
		System.out.println("BB_TI_2.crearPedido()  *** "  + new Date());		

		Pedido pedido = (Pedido)factory.crear("PEDIDO");
		pedido.setIdClient(29);
		pedido.setListProds(shop.getCarrito().getProducts());
		pedido.setLugarEntrega("DESCONOCIDO");
		
		shop.getGestorPedidos().getDao().create(pedido);
		shop.setPedido(pedido);
		
		System.out.println("PEDIDO CREADO  : "  + new Date());	
		System.out.println(" id = " + shop.getPedido().getId());
		System.out.println(" entrega en  = " + shop.getPedido().getLugarEntrega());
		shop.getPedido().getListProds().forEach(e -> System.out.println("articulo : id = " + e.getId() + ", description = " + e.getDescripcion() + ", pedido = " + e.getPedido_id()));;

		
		pedidoDao.create(pedido);
		
//		System.out.println("PEDIDO  REPETIDO CREADO CON PEDIDODAO : "  + new Date());	
//		System.out.println(" id = " + shop.getPedido().getId());
//		System.out.println(" entrega en  = " + shop.getPedido().getLugarEntrega());
//		shop.getPedido().getListProds().forEach(e -> System.out.println("articulo : id = " + e.getId() + ", description = " + e.getDescripcion() + ", pedido = " + e.getPedido_id()));;

		List<Pedido> listaPedidos = pedidoDao.getAll();
		
		System.out.println("LISTA DE PEDIDOS GRABADOS : "  + listaPedidos.size() + "  ***  " + new Date());	
		listaPedidos.forEach(e -> System.out.println("pedido : id = " + e.getId() + ", description = " + e.getLugarEntrega() + ", pedido = " + e.getIdClient()));

		// user_id = 29
		
	}
	
	public void consultaPedidos(){
		List<Pedido> listaPedidos = pedidoDao.getAll();		
		
		listaPedidos.forEach(e -> 
		{
			System.out.println("pedido id = " + e.getId());
			System.out.println("		cliente = " + e.getIdClient());
			System.out.println("		entrega = " + e.getLugarEntrega());
			System.out.println("articulos :");
			List<Articulo> listaArticulos = (List<Articulo>) e.getListProds();
			listaArticulos.forEach(a -> {
				System.out.println("	articulo id = " + a.getId());
				System.out.println("				description = " + a.getDescripcion());
				System.out.println("				pedido = " + a.getPedido_id());				
			});
		});
	}
	

	

}
