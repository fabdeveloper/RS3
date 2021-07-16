package src.manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import src.entity.Oferta;
import src.entityservices.IEntityServices;
import src.entityservices.OfertaServices;
import src.inter.IServiceLocator;
import src.service.ServiceLocator;

public class StockManagerTest {
	
	private Integer id = null;
	private Oferta oferta = null;
	
	private IStockManager stockManager;
	private IServiceLocator serviceLocator;	
	private IEntityServices<Oferta> ofertaServices;
	private Map<Integer, Oferta> dbMap;
	
	ArgumentCaptor<Oferta> ofertaCaptor = ArgumentCaptor.forClass(Oferta.class); 
	ArgumentCaptor<Integer> integerCaptor = ArgumentCaptor.forClass(Integer.class); 
	
	
	@Before
	public void initDependencies() {
		stockManager = new StockManager();		
		ofertaServices = mockOfertaServices();
		serviceLocator = mockServiceLocator();
		
		stockManager.setServiceLocator(serviceLocator);

		dbMap = new HashMap<Integer, Oferta>();
		
		Oferta of1 = new Oferta();
		of1.setId(1);
		of1.setStock(10);
		
		Oferta of2 = new Oferta();
		of2.setId(2);
		of2.setStock(10);
		
		dbMap.put(1, of1);
		dbMap.put(2, of2);
		

	}	
	
	private IServiceLocator mockServiceLocator() {
		IServiceLocator sl = mock(ServiceLocator.class);
		when(sl.getOfertaServices()).thenReturn(ofertaServices);		
		
		return sl;
	}
	
	private IEntityServices<Oferta> mockOfertaServices(){
		IEntityServices<Oferta> ofertaServices = mock(OfertaServices.class);
	
//		when(ofertaServices.read(id)).thenReturn(findById(id));
//		when(ofertaServices.merge(oferta)).thenReturn(mergeOf(oferta));

//		when(ofertaServices.read(integerCaptor.capture())).thenReturn(findById(integerCaptor.getValue()));
//		when(ofertaServices.merge(ofertaCaptor.capture())).thenReturn(mergeOf(ofertaCaptor.getValue()));
		
		
		when(ofertaServices.read(any())).thenAnswer(invocation ->{
			final Object[] namedArgs = invocation.getArguments();
					
			return findById((Integer)namedArgs[0]);
				});
		
		when(ofertaServices.merge(any())).thenAnswer(invocation ->{
					final Object[] namedArgs = invocation.getArguments();
							
					return mergeOf((Oferta)namedArgs[0]);
						});
		
		return ofertaServices;		
	}
	
	
	private Oferta findById(Integer id) {
		Oferta result = null;
		
		result = dbMap.get(id);
		
		return result;
	}
	
	private Oferta mergeOf(Oferta oferta) {
		Oferta result = null;		
		
		dbMap.replace(oferta.getId(), oferta);
		result = dbMap.get(oferta.getId());
		
		return result;
	}
	
	@Test
	public void consumirStockTest() {
		
		Integer id_oferta = 1;
		Integer stock_solicitado = 2;		
		Integer stock_antes;
		Integer stock_despues;
		
		stock_antes = dbMap.get(id_oferta).getStock();		
		stockManager.consumirStock(id_oferta, stock_solicitado);		
		stock_despues = dbMap.get(id_oferta).getStock();
		
		Assert.assertTrue("Error consumiendo stock", stock_antes-stock_despues == stock_solicitado);		
	}	
	
	@Test
	public void recuperarStockTest() {
		
		Integer id_oferta = 1;
		Integer stock_solicitado = 2;		
		Integer stock_antes;
		Integer stock_despues;
		
		stock_antes = dbMap.get(id_oferta).getStock();			
		stockManager.recuperarStock(id_oferta, stock_solicitado);
		stock_despues = dbMap.get(id_oferta).getStock();

		Assert.assertTrue("Error recuperando stock", stock_despues-stock_antes == stock_solicitado);		

	}
	
	
	
	
	

	public IStockManager getStockManager() {
		return stockManager;
	}

	public void setStockManager(IStockManager stockManager) {
		this.stockManager = stockManager;
	}

	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}



}
