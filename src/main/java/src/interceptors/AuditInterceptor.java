package src.interceptors;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Auditor
//@DaoAuditory
@Interceptor
public class AuditInterceptor {
	
	private final static Logger logger = Logger.getLogger("auditInterceptor");

	static{	
		Handler consoleHandler = new ConsoleHandler();
		Handler fileHandler = null;
		try {
			fileHandler = new FileHandler("auditinterceptor.log", true);
		} catch (SecurityException | IOException e) {


			System.out.println("AuditInterceptor - error creando logger filehandler");
			logger.log(Level.SEVERE, "AuditInterceptor - error creando logger filehandler");
			e.printStackTrace();
		}
		if(consoleHandler != null){
			consoleHandler.setLevel(Level.ALL);
			logger.addHandler(consoleHandler);
		}
		if(fileHandler != null){
			SimpleFormatter sf = new SimpleFormatter();
			fileHandler.setFormatter(sf);
			fileHandler.setLevel(Level.ALL);
			logger.addHandler(fileHandler);
		}
		logger.log(Level.INFO, "AuditInterceptor - logger inicializado");



	}

	
	
	@AroundInvoke
	public Object logAudit(InvocationContext ctx){	
		System.out.println("LOGAUDIT INTERCEPTOR - " + new Date());
		Object target = ctx.getTarget();
		Method method = ctx.getMethod();
		Object[] listaParam = ctx.getParameters();
		Map<String, Object>  contextMap = ctx.getContextData();
		
		String log = "AUD - " + new Date() + " - ," +
				"TARGET = " + target.toString() + ", " +
				"METHOD = " + method.getName() + " - " +
				"";
		
		
		logger.log(Level.ALL, log);
		logger.log(Level.INFO, log);

		
		try {
			return ctx.proceed();
		} catch (Exception e) {
			logger.log(Level.ALL,  " EXCEPTION - " + log);
			e.printStackTrace();
		}
		return null;
		
	}
	



}
