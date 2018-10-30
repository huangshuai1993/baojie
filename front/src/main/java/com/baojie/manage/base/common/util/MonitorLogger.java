package com.baojie.manage.base.common.util;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

public class MonitorLogger {

	private final static Logger logger = Logger.getLogger(MonitorLogger.class);
    private final static MonitorLogger monitorLogger = new MonitorLogger();
    
    private MonitorLogger() {
    	
    }
    
    public static MonitorLogger getInstance(){
        return monitorLogger;
    }
    
    public void info(String message){
        logger.info(message + getStackTraceMgr(false));
    }
    
    public void info(String message, Throwable t){
        logger.info(message, t);
    }
    
    public void info(String pattern, Object ... arguments){
        String message = MessageFormat.format(pattern, arguments);
        logger.info(message + getStackTraceMgr(false));
     }
    
    public void info(String pattern, Throwable t, Object ... arguments){
        String message = MessageFormat.format(pattern, arguments);
        logger.info(message, t);
     }
    
    public boolean isInfoEnabled(){
        return logger.isInfoEnabled();   
    }
    
    public void debug(String message){
        logger.debug(message + getStackTraceMgr(false));
    }
    
    public void debug(String message, Throwable t){
        logger.debug(message, t);
    }
    
    public void debug(String pattern, Object ... arguments){
       String message = MessageFormat.format(pattern, arguments);
       logger.debug(message + getStackTraceMgr(false));
    }
    
    public void debug(String pattern, Throwable t, Object ... arguments){
        String message = MessageFormat.format(pattern, arguments);
        logger.debug(message, t);
     }
    
    public boolean isDebugEnabled(){
        return logger.isDebugEnabled();   
    }
    
    protected String getStackTraceMgr(boolean isAll){
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        StackTraceElement ste;
        StringBuilder sb = new StringBuilder();
        for(int i = 3; i < stes.length; i++)
        {
            ste = stes[i];
            sb.append("\r\n");
            sb.append("at ");
            sb.append(ste.getClassName());
            sb.append(".");
            sb.append(ste.getMethodName());
            sb.append("(");
            sb.append(ste.getFileName());
            sb.append(":");
            sb.append(ste.getLineNumber());
            sb.append(")");
            if(!isAll)
                break;
        }
        return sb.toString();
    }
    
}
