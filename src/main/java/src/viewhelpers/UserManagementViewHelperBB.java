package src.viewhelpers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import src.entity.User;

@Named
@SessionScoped
public class UserManagementViewHelperBB extends
		AbstractEntityManagementViewHelper<User> implements Serializable {
	


	@Transactional
	@Override
	public void callCreate() {
		create(getTransferObject().clone());		
	}
	
	@Override
	public User callRead() {
		return read(getTransferObject().clone().getId());
	}

	@Override
	public void callUpdate() {
		update(getTransferObject().clone());		
	}

	@Override
	public void callDelete() {
		delete(getTransferObject().clone());			
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
	
	/***************************************/
	
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
	
	/*******************************/


}
