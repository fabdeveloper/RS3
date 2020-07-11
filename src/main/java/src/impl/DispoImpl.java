package src.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import src.dao.ArticuloDao;
import src.dao.OfertaDao;
import src.dao.ProductDao;
import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;
import src.inter.IDispo;

@SessionScoped
public class DispoImpl implements IDispo, Serializable {
	
	@Inject
	private ProductDao productDao;
	@Inject
	private ArticuloDao articuloDao;
	@Inject
	private OfertaDao ofertaDao;
	
	private Product productoSeleccionado;
	private Articulo articuloSeleccionado;
	
	private Integer id_ultimoProductoCargado;
	private Integer id_ultimoArticuloCargado;
	
	private List<Product> listaProductos;
	private List<Articulo> listaArticulos;
	private List<Oferta> listaOfertas;
	
	
	
	
	@Override
	public List<Product> getListaProductos() {
		
//		if(listaProductos == null){
//			listaProductos = productDao.getAll();
//		}
		return listaProductos;
	}
	@Override
	public List<Articulo> getListaArticulos() {
//		if(productoSeleccionado == null)return null;	
//		
//		listaArticulos = articuloDao.getListaArticulosPorProduct_Id(productoSeleccionado.getId());

//		if(id_ultimoProductoCargado == null || id_ultimoProductoCargado != productoSeleccionado.getId()){
//			listaArticulos = articuloDao.getListaArticulosPorProduct_Id(productoSeleccionado.getId());
//			id_ultimoProductoCargado = productoSeleccionado.getId();
//		}
		return listaArticulos;
	}
	@Override
	public List<Oferta> getListaOfertas() {
//		if(productoSeleccionado == null || articuloSeleccionado == null)return null;	
//		
//		listaOfertas = ofertaDao.getListaOfertasPorArticulo_Id(articuloSeleccionado.getId());
//
//		
//		if(id_ultimoProductoCargado == null || id_ultimoArticuloCargado == null || 
//				id_ultimoProductoCargado != productoSeleccionado.getId() || id_ultimoArticuloCargado != articuloSeleccionado.getId()
//				){
//			listaOfertas = ofertaDao.getListaOfertasPorArticulo_Id(articuloSeleccionado.getId());
//			id_ultimoProductoCargado = productoSeleccionado.getId();
//			id_ultimoArticuloCargado = articuloSeleccionado.getId();
//		}
		
		return listaOfertas;
	}
	@Override
	public void setArticuloSeleccionado(Integer articulo_id) {		
		for(Articulo articulo: getListaArticulos()){
			if(articulo.getId() == articulo_id)
				articuloSeleccionado = articulo;				
		}		
	}
	@Override
	public void setProductoSeleccionado(Integer producto_id) {		
		for(Product product: getListaProductos()){
			if(product.getId() == producto_id)
				productoSeleccionado = product;
		}		
	}
	@Override
	public void init(Integer producto_id, Integer articulo_id) {
		
		setProductoSeleccionado(producto_id);
		setArticuloSeleccionado(articulo_id);		
	}
	@Override
	public boolean isArticuloOk() {
		return articuloSeleccionado == null;
	}
	@Override
	public boolean isProductOk() {
		return productoSeleccionado == null;
	}
	
	@Override
	public void refreshListaProductos(){
		listaProductos = productDao.getAll();
	}
	@Override
	public void refreshListaArticulos(){
		if(productoSeleccionado != null){ listaArticulos = articuloDao.getListaArticulosPorProduct_Id(productoSeleccionado.getId());}
		else listaArticulos = articuloDao.getAll();		
	}
	@Override
	public void refreshListaOfertas(){
		if(articuloSeleccionado != null) listaOfertas = ofertaDao.getListaOfertasPorArticulo_Id(articuloSeleccionado.getId());
		else listaOfertas = ofertaDao.getAll();		
	}
	
	
	



}
