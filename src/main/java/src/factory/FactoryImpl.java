package src.factory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@RequestScoped
public class FactoryImpl implements Factory{
	
	@Inject
	private LibroFactory libroFactory;
	@Inject
	private UserFactory userFactory;
	@Inject
	private OfertaFactory ofertaFactory;
	@Inject
	private ArticuloFactory articuloFactory;
	@Inject
	private PedidoFactory pedidoFactory;
	
	
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
		case "USER":
			loquesea = userFactory.crear();
			break;
		case "OFERTA":
			loquesea = ofertaFactory.crear();
			break;
		case "ARTICULO":
			loquesea = articuloFactory.crear();
			break;

			default:;		
		}		
		return loquesea;
	}

}
