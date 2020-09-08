package src.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IDao<T> {

	public abstract EntityManager getEntityManager();

	public abstract void create(T entity);

	public abstract void edit(T entity);

	public abstract void remove(T entity);

	public abstract T find(Object id);

	public abstract int count();

	public abstract List<T> getAll();

}