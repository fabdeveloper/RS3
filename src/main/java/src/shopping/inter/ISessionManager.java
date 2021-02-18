package src.shopping.inter;

public interface ISessionManager {
	
	public Boolean isClient();
	public String getCallerName();
	public void invalidateSession();
	public void dispatch(String url);
	public void callLogin();
	public Boolean login(String user, String password);
	public Boolean logout();

}
