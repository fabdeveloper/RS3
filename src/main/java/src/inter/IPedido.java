package src.inter;

import java.util.List;

import src.entity.Articulo;
import src.entity.Oferta;
import src.entity.Product;

public interface IPedido {


	public abstract IPedido clone();

	public abstract Integer getId();

	public abstract Integer getIdClient();

	public abstract List<? extends Oferta> getListProds();

	public abstract Float getValor();

	public abstract String getEstadoPago();

	public abstract String getTipoEnvio();

	public abstract String getLugarEntrega();


}