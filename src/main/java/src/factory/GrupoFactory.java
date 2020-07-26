package src.factory;

import javax.enterprise.context.RequestScoped;

import src.entity.Grupo;

@RequestScoped
public class GrupoFactory implements BeanFactory<Grupo>{
	
	private Grupo grupo = new Grupo();

	@Override
	public Grupo crear() {
		return grupo.clone();
	}

}
