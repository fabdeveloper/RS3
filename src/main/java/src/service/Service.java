package src.service;

import java.util.Date;

import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import src.timers.ScheduledBean;


@Path("/service")
public class Service {
	


	
	@GET
	@Path("/get")
	public String getMethod(){
		return " getMethod ";
	}
	
	@GET
	@Path("set")
	public String setMethod(){
		return " setMethod ";
	}
	
	@GET
	@Path("new")
	public String newMethod(){
		return " newMethod ";
	}
	
	@GET
	@Path("cancel")
	public String cancelMethod(){
		return " cancelMethod ";
	}
	
	@Schedule(second="*")
	public void hazloatiempo(){
		System.out.println(new Date() + " - ***************************** TODOVABIEN ***************************** - " + new Date() );
		
	}

	

}
