package src.viewhelpers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import src.entity.Grupo;
import src.entity.Store;
import src.entity.User;
import src.inter.IServiceLocator;

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
	
	
	/**********************************************/
	
	@RolesAllowed("CLIENT")
	public void callCreate(){
		
		IServiceLocator serviceLocator = getEntityServices().getServiceLocator();

		// obtiene el usuario 
		String nombreUsuario = serviceLocator.getSessionContext().getCallerPrincipal().getName();
		User user = serviceLocator.getUserServices().getGestorE().getDao().namedQuery("byName", nombreUsuario);
		
		// crea la tienda
		getTransferObject().setOwner(user);
		create();
		serviceLocator.getEntityManager().flush();
		
		// crea los grupos
		Grupo grupoUsuarios = serviceLocator.getGrupoServices().getGestorE().getFactory().crear();
		Grupo grupoAdmin = serviceLocator.getGrupoServices().getGestorE().getFactory().crear();
		
		grupoUsuarios.setName("USUARIOS-" + getTransferObject().getId());
		grupoUsuarios.setDescription("clientes tienda con id : " + getTransferObject().getId());
		serviceLocator.getGrupoServices().create(grupoUsuarios);
		
		grupoAdmin.setName("ADMIN-" + getTransferObject().getId());
		grupoAdmin.setDescription("administradores tienda con id : " + getTransferObject().getId());
		serviceLocator.getGrupoServices().create(grupoAdmin);	
		
		serviceLocator.getEntityManager().flush();

		
		// agrega los grupos al usuario
		user.addGrupo(grupoUsuarios);
		user.addGrupo(grupoAdmin);
		serviceLocator.getUserServices().update(user);
		
		// agrega los grupos a la tienda
		getTransferObject().addGrupo(grupoUsuarios);
		getTransferObject().addGrupo(grupoAdmin);
		update();
		
		serviceLocator.getEntityManager().flush();
		
		
	}
	
	

}
