package src.timers;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Singleton;



@Startup
@Singleton
public class ScheduledBean {
	
//	@Schedule(second="*", persistent=false)
	public void hazloatiempo(){
		System.out.println(new Date() + " - ***************************** TODOVABIEN ***************************** - " + new Date() );
		
	}
	
	

}
