package src.inter;

public interface IEncripter {
	
	public String encriptStringToSha256(String encriptable) throws Exception;
	
	public String encriptStrintToSha256Base64(String encriptable) throws Exception;

}
