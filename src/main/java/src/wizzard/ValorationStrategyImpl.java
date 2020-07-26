package src.wizzard;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

import src.entity.Oferta;
import src.inter.IValoracionStrategy;

@RequestScoped
public class ValorationStrategyImpl implements IValoracionStrategy {

	@Override
	public Float valora(List lista){
		Float retorno = 0f;
		
		for(Object oferta: lista){
			if(oferta instanceof Oferta)
			retorno += ((Oferta)oferta).getPrecio();
		}
		return retorno;
	}
	
	

}
