package src.dao;

import javax.enterprise.context.RequestScoped;
import src.entity.Pedido;

@RequestScoped
public class PedidoDao extends AbstractDao<Pedido>{

	public PedidoDao() {
		super(Pedido.class);
	}

}
