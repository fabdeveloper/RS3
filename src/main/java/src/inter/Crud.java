package src.inter;

import java.util.List;

public interface Crud <Entity, Id> {
	
	boolean create(Entity entity);
	
	Entity read(Id id);
	
	List<Entity> readAll();
	
	boolean update(Entity entity);
	
	boolean delete(Id id);

}
