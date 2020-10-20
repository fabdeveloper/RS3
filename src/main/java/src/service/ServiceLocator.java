package src.service;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import src.entity.Articulo;
import src.entity.Grupo;
import src.entity.Oferta;
import src.entity.Product;
import src.entity.User;
import src.entityservices.IEntityServices;
import src.inter.IServiceLocator;

@Singleton
public class ServiceLocator implements IServiceLocator{
	
	@Resource
	private SessionContext ctx;
	
	@PersistenceContext(unitName="RS3_PU")
	private EntityManager em;
	
	@Inject
	private IEntityServices<Grupo> grupoServices;
	@Inject
	private IEntityServices<User> userServices;
	@Inject
	private IEntityServices<Product> productServices;
	@Inject
	private IEntityServices<Articulo> articuloServices;
	@Inject
	private IEntityServices<Oferta> ofertaServices;
	
	
	@Override 
	public SessionContext getSessionContext(){
		return ctx;
	}
	
	@Override
	public EntityManager getEntityManager(){
		return em;
	}
	
	@Override
	public IEntityServices<Grupo> getGrupoServices(){
		return grupoServices;
	}
	
	@Override
	public IEntityServices<User> getUserServices() {
		return userServices;
	}

	@Override
	public IEntityServices<Product> getProductServices() {
		return productServices;
	}

	@Override
	public IEntityServices<Articulo> getArticuloServices() {
		return articuloServices;
	}
	
	@Override
	public IEntityServices<Oferta> getOfertaServices() {
		return ofertaServices;
	}



}
