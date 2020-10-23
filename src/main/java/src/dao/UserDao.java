package src.dao;

import javax.enterprise.context.RequestScoped;
import src.entity.User;

@RequestScoped
public class UserDao extends AbstractDao<User>{
	
	public UserDao() {
		super(User.class);
	}
}
