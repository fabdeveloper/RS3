package src.inter;

public interface IProcessable {
	
	public String process(Object obj);
	
	public default String process(){
		return null;
	}

}
