package src.interceptors;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@Interceptor
public class AuditInterceptor {
	
	static Logger logger = Logger.getLogger(AuditInterceptor.class.getName());
	
	
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
