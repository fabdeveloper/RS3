package src.viewhelpers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import src.entity.Grupo;
import src.entity.Store;
import src.entity.User;

@Named
@SessionScoped
public class StoreManagementViewHelperBB extends
		AbstractEntityManagementViewHelper<Store> implements Serializable{


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

	public User getOwner() {
		return getTransferObject().getOwner();
	}

	public void setOwner(User owner) {
		getTransferObject().setOwner(owner);
	}

	public List<Grupo> getListaGrupos() {
		return getTransferObject().getListaGrupos();
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		getTransferObject().setListaGrupos(listaGrupos);
	}

}
