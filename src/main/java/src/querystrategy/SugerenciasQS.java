/**
 * 
 */
package src.querystrategy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import src.entity.Etiqueta;
import src.entity.Oferta;
import src.inter.IServiceLocator;

/**
 * @author fabo_
 *
 */
public class SugerenciasQS implements IQueryStrategy<Oferta> {
	
	
	private IServiceLocator serviceLocator;
	
	

	/**
	 * @param serviceLocator
	 */
	public SugerenciasQS(IServiceLocator serviceLocator) {
		super();
		this.serviceLocator = serviceLocator;
	}

	/**
	 * 
	 */
	public SugerenciasQS() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Oferta> executeStrategy() {
		return sugerenciasAvail();
	}
	
	public List<Oferta> sugerenciasAvail() {
		// listado de ofertas sugeridas, navidad, sanvalentin, halloween, etc
		List<Oferta> listaSugerencias = new ArrayList<Oferta>();
		
		// etiquetas where startDate < sysdate < stopDate
		List<Etiqueta> etiquetasValidasEncontradas = serviceLocator.getEtiquetaServices().createNamedQueryLimited("etiquetasActivasHoy", 15);

		for(Etiqueta etiqueta : etiquetasValidasEncontradas) {
			// ofertas where oferta and etiqueta in jointable
			List<Oferta> listaOfertasConEtiqueta = serviceLocator.getOfertaServices().createNamedQueryListResult("ofertasConEtiquetaActiva", "etiqueta_id", etiqueta.getId().toString());
			for(Oferta oferta : listaOfertasConEtiqueta) {
				listaSugerencias.add(oferta);
			}
		}
		
		return listaSugerencias;
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

}
