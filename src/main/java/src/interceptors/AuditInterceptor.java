package src.interceptors;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import src.inter.IServiceLocator;

@Auditor
//@DaoAuditory
@Interceptor
public class AuditInterceptor implements Serializable{
	
	private final static Logger logger = Logger.getLogger("auditInterceptor");
	
	@Inject 
	private IServiceLocator serviceLocator;

	static{	
		Handler consoleHandler = new ConsoleHandler();
		Handler fileHandler = null;
		try {
			fileHandler = new FileHandler("auditinterceptor.log", true);
		} catch (SecurityException | IOException e) {
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
		logger.log(Level.INFO, "LOGAUDIT INTERCEPTOR - " + new Date());
		Object target = ctx.getTarget();
		Method method = ctx.getMethod();
		Object[] listaParam = ctx.getParameters();
		Map<String, Object>  contextMap = ctx.getContextData();		

//		HttpServletRequest request = serviceLocator.getRequest();
//		if(request != null){
//			String ip = serviceLocator.getRequest().getRemoteAddr();
//			String remoteAddr = serviceLocator.getRequest().getHeader("X-FORWARDED-FOR");
//			logger.log(Level.ALL, "ip = " + ip + ", remoteAddr = " + remoteAddr);
//
//			Enumeration<String> nombres = request.getHeaderNames();
//			while(nombres.hasMoreElements()){
//				String nombre = nombres.nextElement();
//				String texto = request.getHeader(nombre);
//				logger.log(Level.ALL, "nombre = " + nombre + ", texto = " + texto);
//			}			
//		}
		
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
