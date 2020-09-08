package src.dao;

import javax.enterprise.context.RequestScoped;
import src.entity.Grupo;

@RequestScoped
public class GrupoDao extends AbstractDao<Grupo> {

	public GrupoDao() {
		super(Grupo.class);
	}

}
