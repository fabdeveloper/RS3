package src.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.dao.ArticuloDao;
import src.dao.LibroDao;
import src.dao.OfertaDao;
import src.dao.PedidoDao;
import src.dao.PlantaDao;
import src.dao.ProductDao;
import src.dao.UserDao;
import src.entity.Articulo;
import src.entity.Libro;
import src.entity.Oferta;
import src.entity.Pedido;
import src.entity.Planta;
import src.entity.Product;
import src.entity.User;
import src.factory.FactoryImpl;
import src.wizzard.ShopFacade;


@Named
@SessionScoped
public class BB_TI_3 implements Serializable{
	
	@Inject private ShopFacade shop;
	
	private Integer idProducto = 0;
	private Integer idArticulo = 1;
	private Integer idOferta = 2;
	
	private String email = "email";
	private String name = "name";
	private String password = "password";

	
	@Inject private FactoryImpl factory;
	
	@Inject private ProductDao productDao;
	@Inject private ArticuloDao articuloDao;
	@Inject private LibroDao libroDao;
	@Inject private PlantaDao plantaDao;
	@Inject private OfertaDao ofertaDao;
	@Inject private UserDao userDao;
	@Inject private PedidoDao pedidoDao;
	
	private User usuario;

	
	private Product p1;
	private Product p2;
	
	private Articulo a1;
	private Articulo a2;
	private Articulo a3;
	private Articulo a4;
	
	private Oferta o1;
	private Oferta o2;
	private Oferta o3;
	private Oferta o4;
	private Oferta o5;
	private Oferta o6;
	private Oferta o7;



	
	@Transactional
	public void crearProductos(){
		p1 = (Product)factory.crear("PRODUCT");
		p1.setName("LIBROS");
		p1.setTipo("LIBRERIA");
		productDao.create(p1);
				
		p2 = (Product)factory.crear("PRODUCT");
		p2.setName("PLANTAS");
		p2.setTipo("JARDIN");
		productDao.create(p2);		
		
	}
	
	@Transactional
	public void crearArticulos(){
		Libro libro1 = (Libro)factory.crear("LIBRO");
		libro1.setProduct(p1);
		libro1.setAuthor("Verne");
		libro1.setTitle("Viaje");
		libro1.setPrice(27f);		
		libroDao.create(libro1);	
		a1 = libro1;
		
		Libro libro2 = (Libro)factory.crear("LIBRO");
		libro2.setProduct(p1);
		libro2.setAuthor("Yo");
		libro2.setTitle("super genial");
		libro2.setPrice(55f);		
		libroDao.create(libro2);	
		a2 = libro2;
		
		Libro libro3 = (Libro)factory.crear("LIBRO");
		libro3.setProduct(p1);
		libro3.setAuthor("Un vecino");
		libro3.setTitle("Barrio");
		libro3.setPrice(16f);	
		libroDao.create(libro3);	
		a3 = libro3;
		
		
		Planta planta1 = (Planta)factory.crear("PLANTA");
		planta1.setProduct(p2);
		planta1.setEspecie("trepadora");
		planta1.setNombreComercial("tomate");
		planta1.setPrice(7f);
		planta1.setDescripcion("en maceta");
		plantaDao.create(planta1);
		a4 = planta1;
		
	}
	
	@Transactional
	public void crearOfertas(){
		
		Oferta oferta1 = (Oferta)factory.crear("OFERTA");
		oferta1.setArticulo(a1);
		oferta1.setName("Primavera");
		oferta1.setPrecio(25f);
		ofertaDao.create(oferta1);
		o1 = oferta1;
		
		Oferta oferta2 = (Oferta)factory.crear("OFERTA");
		oferta2.setArticulo(a1);
		oferta2.setName("Verano");
		oferta2.setPrecio(20f);
		ofertaDao.create(oferta2);
		o2 = oferta2;
		
		Oferta oferta3 = (Oferta)factory.crear("OFERTA");
		oferta3.setArticulo(a4);
		oferta3.setName("Invierno");
		oferta3.setPrecio(15f);
		ofertaDao.create(oferta3);
		o1 = oferta3;		
	}
	
	public void verPedidos(){
		for(Pedido pedido: pedidoDao.getAll()){
			System.out.println("pedido - id : " + pedido.getId() + ", id_client : " + pedido.getIdClient() + ", valor : " + pedido.getValor());
			for(Oferta oferta: pedido.getListProds()){
				System.out.println(" oferta - id : " + oferta.getId() + ", name : " + oferta.getName() + ", precio : " + oferta.getPrecio() + ", articulo_id" + oferta.getArticulo().getId());

			}
		};
	}
	
	@Transactional
	public void grabarPedido(){
		shop.setUser(usuario);
		shop.crearPedido();
		Pedido pedido = (Pedido)shop.getPedido();
		System.out.println("PEDIDO : " + new Date());
		System.out.println("id : " + pedido.getId() + ", id_client : " + pedido.getIdClient() + ", valoracion : " + pedido.getValor());

		for(Oferta oferta: pedido.getListProds()){
			System.out.println("oferta - id :  " + oferta.getId() + ", name : " + oferta.getName() + ", precio : " + oferta.getPrecio());

		}		
	}	
	@Transactional
	public void crearUsuario(){		
		usuario = (User)factory.crear("USER");
		usuario.setEmail(email);
		usuario.setName(name);
		usuario.setPassword(password);
		userDao.create(usuario);		
	}
	
	public void verProductos(){		
		List<Product> listaProductos = shop.getListaProductos();
		System.out.println("PRODUCTOS DISPONIBLES " + new Date() );
		listaProductos.forEach(e -> {
			System.out.println("id: " + e.getId() + ", name: " + e.getName() + ", tipo: " + e.getTipo()); });
		
	}
	
	public void verArticulos(){
		List<Articulo> listaArticulos = shop.getListaArticulos();
		System.out.println("ARTICULOS DISPONIBLES " + new Date() );
		listaArticulos.forEach(e -> {
			System.out.println("id: " + e.getId() + ", name: " + e.getName() + ", id_producto: " + e.getProduct().getId() + ", price: " + e.getPrice() + ", description: " + e.getDescripcion()); });
	}
	
	public void verOfertas(){
		List<Oferta> listaOfertas = shop.getListaOfertas();
		System.out.println("OFERTAS DISPONIBLES " + new Date() );
		listaOfertas.forEach(e -> {
			System.out.println("id: " + e.getId() + ", name: " + e.getName() + ", id_articulo: " + e.getArticulo().getId() + ", precio: " + e.getPrecio() + ", descripcion: " + e.getDescripcion()); });
		
	}
	
	public void seleccionarProducto(){
		shop.seleccionarProducto(idProducto);
	}
	
	public void seleccionarArticulo(){
		shop.seleccionarArticulo(idArticulo);
		
	}
	
	public void seleccionarOferta(){
		shop.seleccionarOferta(idOferta);
		
	}
	
	public void verCarrito(){
		List<Oferta> listaOfertas = shop.getCarrito().getProducts();
		System.out.println("CARRITO :  " + new Date() );
		listaOfertas.forEach(e -> {
		System.out.println("oferta - id: " + e.getId() + ", name: " + e.getName() + ", id_articulo: " + e.getArticulo().getId() + ", precio: " + e.getPrecio() + ", descripcion: " + e.getDescripcion()); });
	
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Integer getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Integer idOferta) {
		this.idOferta = idOferta;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
