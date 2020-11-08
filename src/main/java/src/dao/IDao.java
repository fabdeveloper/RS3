package src.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IDao<T> {

	public abstract EntityManager getEntityManager();
	
	public T createNamedQuery(String queryname, String paramname, String paramvalue);

	public List<T> createNamedQueryListResult(String queryname, String paramname, String paramvalue);

	public List<T> createNamedQueryListResultIntParam(String queryname, String paramname, Integer paramvalue);

	public abstract void create(T entity);

	public abstract void edit(T entity);

	public abstract void remove(T entity);

	public abstract T find(Object id);

	public abstract int count();

	public abstract List<T> getAll();

}