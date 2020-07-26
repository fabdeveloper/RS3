package src.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@RequestScoped
public class FactoryImpl implements IFactory{
	
	@Inject
	private LibroFactory libroFactory;
	@Inject
	private PlantaFactory plantaFactory;
	@Inject
	private UserFactory userFactory;
	@Inject
	private OfertaFactory ofertaFactory;
	@Inject
	private ArticuloFactory articuloFactory;
	@Inject
	private PedidoFactory pedidoFactory;
	@Inject
	private ProductFactory productFactory;
	@Inject
	private GrupoFactory grupoFactory;
	
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
