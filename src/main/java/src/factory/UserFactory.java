package src.factory;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

import src.entity.User;
import src.inter.IUser;


@RequestScoped
public class UserFactory implements BeanFactory<User>{
	
	private User user = new User();

	@Override
	public User crear() {
		return (User)user.clone();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
