package src.viewhelpers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Grupo;
import src.entity.Store;
import src.entity.User;
import src.inter.IServiceLocator;

@Named
@SessionScoped
public class StoreManagementViewHelperBB extends
		AbstractEntityManagementViewHelper<Store> implements Serializable{

	
	
	private String ownerName;	

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	/*********************************************/

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
	
	@Transactional
	public void callCreate(){
		
		IServiceLocator serviceLocator = getEntityServices().getServiceLocator();

		// obtiene el usuario 
//		String ownerName = serviceLocator.getSessionContext().getCallerPrincipal().getName();
		User user = serviceLocator.getUserServices().getGestorE().getDao().createNamedQuery("byName", "nombre", ownerName);
		
		// crea la tienda
		getTransferObject().setOwner(user);
		create();
		serviceLocator.getEntityManager().flush();
		
		// crea los grupos
		Grupo grupoUsuarios = serviceLocator.getGrupoServices().getGestorE().getFactory().crear();
		Grupo grupoAdmin = serviceLocator.getGrupoServices().getGestorE().getFactory().crear();
		
		grupoUsuarios.setName("USERGROUP-" + getTransferObject().getId());
		grupoUsuarios.setDescription("clientes tienda con id : " + getTransferObject().getId());
		serviceLocator.getGrupoServices().create(grupoUsuarios);
		
		grupoAdmin.setName("ADMINGROUP-" + getTransferObject().getId());
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
		
		// log datos
		Store store = read();
		System.out.println("CREADO STORE - id : " + store.getId() + ", name : " + store.getName() + ", description : " + store.getDescription() );
		System.out.println("	OWNER - id : " + store.getOwner().getId() + ", name : " + store.getOwner().getName() + ", email : " + store.getOwner().getEmail() );
		for(Grupo grupo : store.getListaGrupos()){
			System.out.println("		GRUPO  - id : " + grupo.getId() + ", name : " + grupo.getName() + ", description : " + grupo.getDescription() );

		}


		
		
	}
	
	

}
