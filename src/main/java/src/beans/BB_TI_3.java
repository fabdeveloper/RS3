package src.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import src.dao.ArticuloDao;
import src.dao.LibroDao;
import src.dao.OfertaDao;
import src.dao.PlantaDao;
import src.dao.ProductDao;
import src.dao.UserDao;
import src.entity.Articulo;
import src.entity.Libro;
import src.entity.Oferta;
import src.entity.Planta;
import src.entity.Product;
import src.factory.FactoryImpl;
import src.wizzard.ShopFacade;


@Named
@SessionScoped
public class BB_TI_3 implements Serializable{
	
	@Inject private ShopFacade shop;
	
	private Integer idProducto;
	private Integer idArticulo;
	private Integer idOferta;

	
	@Inject private FactoryImpl factory;
	
	@Inject private ProductDao productDao;
	@Inject private ArticuloDao articuloDao;
	@Inject private LibroDao libroDao;
	@Inject private PlantaDao plantaDao;
	@Inject private OfertaDao ofertaDao;
	@Inject private UserDao userDao;
	
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
	
	public void crearArticulos(){
		Libro libro1 = (Libro)factory.crear("LIBRO");
		libro1.setProduct_id(p1.getId());
		libro1.setAuthor("Verne");
		libro1.setTitle("Viaje");
		libro1.setPrice(27f);		
		libroDao.create(libro1);	
		a1 = libro1;
		
		Libro libro2 = (Libro)factory.crear("LIBRO");
		libro2.setProduct_id(p1.getId());
		libro2.setAuthor("Yo");
		libro2.setTitle("super genial");
		libro2.setPrice(55f);		
		libroDao.create(libro2);	
		a2 = libro2;
		
		Libro libro3 = (Libro)factory.crear("LIBRO");
		libro3.setProduct_id(p1.getId());
		libro3.setAuthor("Un vecino");
		libro3.setTitle("Barrio");
		libro3.setPrice(16f);	
		libroDao.create(libro3);	
		a3 = libro3;
		
		
		Planta planta1 = (Planta)factory.crear("PLANTA");
		planta1.setProduct_id(p2.getId());
		planta1.setEspecie("trepadora");
		planta1.setNombreComercial("tomate");
		planta1.setPrice(7f);
		planta1.setDescripcion("en maceta");
		plantaDao.create(planta1);
		a4 = planta1;
		
	}
	
	public void crearOfertas(){
		
		Oferta oferta1 = (Oferta)factory.crear("OFERTA");
		oferta1.setArticulo_id(a1.getId());
		oferta1.setProduct_id(a1.getProduct_id());
		oferta1.setName("Primavera");
		oferta1.setPrecio(25f);
		ofertaDao.create(oferta1);
		o1 = oferta1;
		
		Oferta oferta2 = (Oferta)factory.crear("OFERTA");
		oferta2.setArticulo_id(a1.getId());
		oferta2.setProduct_id(a1.getProduct_id());
		oferta2.setName("Verano");
		oferta2.setPrecio(20f);
		ofertaDao.create(oferta2);
		o2 = oferta2;
		
		Oferta oferta3 = (Oferta)factory.crear("OFERTA");
		oferta3.setArticulo_id(a4.getId());
		oferta3.setProduct_id(a4.getProduct_id());
		oferta3.setName("Invierno");
		oferta3.setPrecio(15f);
		ofertaDao.create(oferta3);
		o1 = oferta3;
		
		
		
		
		
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
			System.out.println("id: " + e.getId() + ", name: " + e.getName() + ", id_producto: " + e.getProduct_id() + ", price: " + e.getPrice() + ", description: " + e.getDescripcion()); });
	}
	
	public void verOfertas(){
		List<Oferta> listaOfertas = shop.getListaOfertas();
		System.out.println("OFERTAS DISPONIBLES " + new Date() );
		listaOfertas.forEach(e -> {
			System.out.println("id: " + e.getId() + ", name: " + e.getName() + ", id_articulo: " + e.getArticulo_id() + ", precio: " + e.getPrecio() + ", descripcion: " + e.getDescripcion() + ", id_producto: " + e.getProduct_id()); });
		
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
	
	public void grabarPedido(){
		shop.crearPedido();
	}
	
	
	
	

}
