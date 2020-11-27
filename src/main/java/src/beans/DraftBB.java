package src.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import src.inter.IProcessable;


@Named
@SessionScoped
public class DraftBB implements Serializable, IProcessable {
	
	private String pagina = "";
	private String estado = "";
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	@Override
	public String process(Object obj) {
		return null;
	}
	
	@Override
	public String process(){
		
		switch(estado){		
		case "1":
			pagina = "../testdef.xhtml";
			break;
		case "2":
			pagina = "../draft/textdraft.xhtml";
			break;
		case "3":
			pagina = "../index.jsp";
			break;
			default:
				;
		}
		
		return null;
	}
	
	
	
	
	

}
