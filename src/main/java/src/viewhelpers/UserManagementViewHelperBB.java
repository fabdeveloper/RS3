package src.viewhelpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.Grupo;
import src.entity.User;

@Named
@SessionScoped
public class UserManagementViewHelperBB extends
		AbstractEntityManagementViewHelper<User> implements Serializable {
	
	private Integer idGrupo;

	@Override
	public User getTransferObjectClone() {
		return getTransferObject().clone();
	}

	@Transactional
	public void callCreate(){
		Grupo grupo = getServiceLocator().getGrupoServices().read(idGrupo);
		List<Grupo> lista = new ArrayList<Grupo>();
		lista.add(grupo);
		getTransferObject().setListaGrupos(lista);
//		User user = getTransferObject().clone();
//		getEntityServices().create(user);
//		getEntityServices().update(user);
		
		create();

//		System.out.println("CREADO USER con id = " + user.getId());
	}
	
	public void callReadAll(){
		for(User user : super.readAll()){
			System.out.println("USER - id = " + user.getId() + ", name = " + user.getName() + ", email = " + user.getEmail() + ", pass = " + user.getPassword());

			for(Grupo grupo : user.getListaGrupos()){
				System.out.println("GRUPO - id = " + grupo.getId() + ", name = " + grupo.getName() + ", descripcion = " + grupo.getDescription());

			}
		}		
	}



	
	/**********************************************/
	// GETTERS AND SETTERS
	
	public void setId(Integer id){
		getTransferObject().setId(id);
	}
	
	public void setName(String name){
		getTransferObject().setName(name);
	}
	
	public void setEmail(String email){
		getTransferObject().setEmail(email);
	}
	
	public void setPassword(String password){
		getTransferObject().setPassword(password);
	}
	
	public void setListaGrupos(List<Grupo> listaGrupos) {
		getTransferObject().setListaGrupos(listaGrupos);
	}
	
	/***************************************/
	
	@Override
	public Integer getId(){
		return getTransferObject().getId();
	}
	
	public String getName(){
		return getTransferObject().getName();
	}
	
	public String getEmail(){
		return getTransferObject().getEmail();
	}
	
	public String getPassword(){
		return getTransferObject().getPassword();
	}	
	
	public List<Grupo> getListaGrupos() {
		return getTransferObject().getListaGrupos();
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	/*******************************/


	
}
