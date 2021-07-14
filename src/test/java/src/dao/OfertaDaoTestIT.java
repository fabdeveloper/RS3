package src.dao;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import src.entity.Oferta;

public class OfertaDaoTestIT {
	
	private OfertaDao ofertaDao;
	private EntityTransaction et;
	
	
//	@Before
	public void initializeDependencies(){
		
//		ofertaDao = new OfertaDao();
//		ofertaDao.setEm(Persistence.createEntityManagerFactory("integration").createEntityManager());
//		et = ofertaDao.getEm().getTransaction();
	}
	
//	@Test
	public void grabarOferta(){
		Oferta oferta = new Oferta();
		oferta.setName("TEST");
		int numRegistrosAntes = ofertaDao.count();
		et.begin();
		ofertaDao.persist(oferta);
		et.commit();
		int numRegistrosDespues = ofertaDao.count();
		for(Oferta registro: ofertaDao.getAll()){
			System.out.println("oferta - id : " + registro.getId() + ", name : " + registro.getName());
		}
		assert numRegistrosDespues>numRegistrosAntes : "no se incrementa ";
	}

}
