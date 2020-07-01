package src.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import src.entity.Libro;
import src.entity.User;
import src.factory.FactoryImpl;
import src.inter.IUser;

@Named
@SessionScoped
//@DeclareRoles({"CLIENT","PARTNER"})
public class BackB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FactoryImpl factory;
	@Inject
	private LibroFacade libroFacade;
	@Inject
	private UserFacade userFacade;
	
	
	public void grabarUsuario(){
		User usuario = (User)factory.crear("USER");
		usuario.setEmail("test@test.com");
		usuario.setName("test");
		usuario.setPassword("test");
		userFacade.create(usuario);
		System.out.println("BackB.grabarUsuario() " + new Date() + " ****************");

	}
	
	public void cuantosUsuarios(){
		System.out.println("BackB.cuantosUsuarios() " + new Date() + " - NUMERO DE USERS = " + userFacade.count());

	}
	
	public void grabarLibro(){
		
		System.out.println("BackB.grabarLibro() " + new Date() + " ****************");
		
		Libro libro = (Libro)factory.crear("LIBRO");
		libro.setAuthor("Yo");
		libro.setTitle("La Cosa");
		libro.setTipo("LIBRO");
		libro.setName("libro");				
		
		libroFacade.create(libro);
	}
	
	public void cuantosLibros(){
		System.out.println("BackB.cuantosLibros() - LibroFacade " + new Date() + " - NUMERO DE LIBROS = " + libroFacade.count());
	
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LibroFacade getLibroFacade() {
		return libroFacade;
	}

	public void setLibroFacade(LibroFacade libroFacade) {
		this.libroFacade = libroFacade;
	}	
	
//	@RolesAllowed("CLIENT")
//	public void isClient(){
//		System.out.println("BackB.isClient() " + new Date() + " - ES UN CLIENTE ");		
//	}
//	
//	@RolesAllowed("PARTNER")
//	public void isPartner(){
//		System.out.println("BackB.isPartner() " + new Date() + " - ES UN PARTNER ");		
//	}
	
	

}
