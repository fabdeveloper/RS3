package src.timers;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;


@Startup
@Singleton
public class TimedBean {
	
	@Resource
	TimerService ts;
	
	
	@PostConstruct
	public void init(){
//		ts.createIntervalTimer(1000, 1000, new TimerConfig());
	}
	
	
	@Timeout
	public void timeout(){
		System.out.println(new Date() + " - ***************************** CUCU ***************************** - " + new Date() );
	}


	public TimerService getTs() {
		return ts;
	}


	public void setTs(TimerService ts) {
		this.ts = ts;
	}
	
	

}
