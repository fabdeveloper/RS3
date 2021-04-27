/**
 * 
 */
package src.transferobject;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import src.entity.Oferta;

/**
 * @author fabo_
 *
 */
public class OfertaViewTO extends EntityViewTransferObject {
	
	public static OfertaViewTO getNewOfertaViewTO(Oferta oferta) {
		
		OfertaViewTO to = new OfertaViewTO();
		
		to.setOfid(oferta.getId().toString());
		
		to.setTexttop(oferta.getName());
		to.setTextcenter(oferta.getDescripcion());
		to.setTextbottom(oferta.getArticulo().getName());
		
		to.setTextobotonenviar("compra");
		to.setUrlimage(oferta.getUrlImage());
	
		
		return to;
	}
	
	public static List<OfertaViewTO> getList(List<Oferta> listaofertas){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("listaofertas = " + listaofertas));
		List<OfertaViewTO> listaRespuesta = new ArrayList<OfertaViewTO>();
		for(Oferta oferta : listaofertas) {
			listaRespuesta.add(OfertaViewTO.getNewOfertaViewTO(oferta));
		}
		return listaRespuesta;
	}
	


}
