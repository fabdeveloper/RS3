package src.viewhelpers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import src.entity.Grupo;

@Named
@SessionScoped
public class GroupManagementViewHelperBB extends
		AbstractEntityManagementViewHelper<Grupo> implements Serializable{

	

	
	/****************************************/
	// GETTERS AND SETTERS
	
	
	@Override
	public Integer getId() {
		return getTransferObject().getId();
	}
	
	public void setId(Integer id) {
		getTransferObject().setId(id);
	}

	public String getName() {
		return getTransferObject().getName();
	}

	public void setName(String name) {
		getTransferObject().setName(name);
	}

	public String getDescription() {
		return getTransferObject().getDescription();
	}

	public void setDescription(String description) {
		getTransferObject().setDescription(description);
	}
	
	/*****************************************/
	
	
	public void verTodos(){
		for(Grupo grupo: readAll()){
			System.out.println("GRUPO - id = " + grupo.getId() + ", name = " + grupo.getName() + ", descripcion = " + grupo.getDescription());

		}
	}
	

	


}
