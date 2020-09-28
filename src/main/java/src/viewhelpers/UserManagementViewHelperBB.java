package src.viewhelpers;

import java.io.Serializable;
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
	

	@Override
	public User getTransferObjectClone() {
		return getTransferObject().clone();
	}

	
	public void callReadAll(){
		for(User user : super.readAll()){
			System.out.println("USER - id = " + user.getId() + ", name = " + user.getName() + ", email = " + user.getEmail() + ", pass = " + user.getPassword());
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
	
	/*******************************/


}
