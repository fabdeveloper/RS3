/**
 * 
 */
package src.transferobject;

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

}
