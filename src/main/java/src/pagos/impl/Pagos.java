package src.pagos.impl;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import src.pagos.inter.IPagos;
import src.shopping.inter.IShoppingFacade;

@Path("/pagos")
public class Pagos implements IPagos {
	
	@Inject
	private IShoppingFacade shoppingFacade;

	@GET
	@Path("/pagook")
	@Override
	public String pagoOK() {		
		return shoppingFacade.setPaymentProcessOK(true);
	}

	@GET
	@Path("/pagoko")
	@Override
	public String pagoKO() {
		return shoppingFacade.setPaymentProcessOK(false);
	}

	public IShoppingFacade getShoppingFacade() {
		return shoppingFacade;
	}

	public void setShoppingFacade(IShoppingFacade shoppingFacade) {
		this.shoppingFacade = shoppingFacade;
	}
	
	

}
