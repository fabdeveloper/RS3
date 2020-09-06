package src.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Grupo;
import src.entity.Libro;
import src.entity.Oferta;
import src.entity.Pedido;
import src.entity.Planta;
import src.entity.Product;
import src.entity.User;


@RequestScoped
public class FactoryImpl implements IFactory{
	
	@Inject
	private BeanFactory<Libro> libroFactory; // LibroFactory
	@Inject
	private BeanFactory<Planta> plantaFactory; // PlantaFactory
	@Inject
	private BeanFactory<User> userFactory; // UserFactory
	@Inject
	private BeanFactory<Oferta> ofertaFactory; //OfertaFactory
	@Inject
	private BeanFactory<Articulo> articuloFactory; // ArticuloFactory
	@Inject
	private BeanFactory<Pedido> pedidoFactory; // PedidoFactory
	@Inject
	private BeanFactory<Product> productFactory; // ProductFactory
	@Inject
	private BeanFactory<Grupo> grupoFactory; // GrupoFactory
	
	@Override
	public Object crear(String type) {
		Object loquesea = null;
		
		switch(type){
		case "PEDIDO":
			loquesea = pedidoFactory.crear();
			break;
		case "LIBRO":
			loquesea = libroFactory.crear();
			break;
		case "PLANTA":
			loquesea = plantaFactory.crear();
			break;
		case "USER":
			loquesea = userFactory.crear();
			break;
		case "OFERTA":
			loquesea = ofertaFactory.crear();
			break;
		case "ARTICULO":
			loquesea = articuloFactory.crear();
			break;
		case "PRODUCT":
			loquesea = productFactory.crear();
			break;
		case "GRUPO":
			loquesea = grupoFactory.crear();
			break;

			default:;		
		}		
		return loquesea;
	}

}
