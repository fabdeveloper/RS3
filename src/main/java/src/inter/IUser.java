package src.inter;

public interface IUser {

	public abstract IUser clone();

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract String getPassword();

	public abstract void setPassword(String password);

}