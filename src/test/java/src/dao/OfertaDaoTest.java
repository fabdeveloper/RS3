package src.dao;

import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import src.entity.Oferta;

public class OfertaDaoTest {
	
	private OfertaDao ofertaDao;
	
	
	@Before
	public void initializeDependencies(){
		
//		ofertaDao = new OfertaDao();
//		ofertaDao.setEm(Persistence.createEntityManagerFactory("MyPU").createEntityManager());
//		
	}
	
//	@Test
	public void grabarOferta(){
		Oferta oferta = new Oferta();
		oferta.setName("TEST");
		int numRegistrosAntes = ofertaDao.count();
		ofertaDao.create(oferta);
		int numRegistrosDespues = ofertaDao.count();
		for(Oferta registro: ofertaDao.getAll()){
			System.out.println("oferta - id : " + registro.getId() + ", name : " + registro.getName());
		}
		assert numRegistrosDespues>numRegistrosAntes : "no se incrementa ";
	}

}
