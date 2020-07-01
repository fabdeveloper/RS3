package src.factory;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

import src.entity.Pedido;
import src.inter.IPedido;

//@Singleton
//@Stateless
@RequestScoped
public class PedidoFactory implements ProductFactory<Pedido>{
	
	private IPedido pedido = new Pedido();

	@Override
	public Pedido crear() {
		return (Pedido)pedido.clone();
	}

	public IPedido getPedido() {
		return pedido;
	}

	public void setPedido(IPedido pedido) {
		this.pedido = pedido;
	}
	
	
	

}
