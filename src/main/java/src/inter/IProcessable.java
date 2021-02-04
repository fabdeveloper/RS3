package src.inter;

public interface IProcessable {
	
	public default String process(Object obj){
		return null;
	}
	
	public default String process(){
		return null;
	}

}
