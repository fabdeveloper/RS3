package src.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import src.entity.Articulo;
import src.entity.Grupo;
import src.entity.Oferta;
import src.entity.Pedido;
import src.entity.Product;
import src.entity.User;


@RequestScoped
public class FactoryImpl implements IFactory{
	

	@Inject
	private IBeanFactory<User> userFactory; // UserFactory
	@Inject
	private IBeanFactory<Oferta> ofertaFactory; //OfertaFactory
//	@Inject
//	private IBeanFactory<Articulo> articuloFactory; // ArticuloFactory
	@Inject
	private IBeanFactory<Pedido> pedidoFactory; // PedidoFactory
	@Inject
	private IBeanFactory<Product> productFactory; // ProductFactory
	@Inject
	private IBeanFactory<Grupo> grupoFactory; // GrupoFactory
	
	@Override
	public Object crear(String type) {
		Object loquesea = null;
		
		switch(type){
		case "PEDIDO":
			loquesea = pedidoFactory.crear();
			break;

		case "USER":
			loquesea = userFactory.crear();
			break;
		case "OFERTA":
			loquesea = ofertaFactory.crear();
			break;
//		case "ARTICULO":
//			loquesea = articuloFactory.crear();
//			break;
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
