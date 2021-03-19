/**
 * 
 */
package src.dao;

import javax.enterprise.context.RequestScoped;

import src.entity.Etiqueta;

/**
 * @author fabo_
 *
 */

@RequestScoped
public class EtiquetaDao extends AbstractDao<Etiqueta> {
	
	public EtiquetaDao() {
		super(Etiqueta.class);
	}

}
