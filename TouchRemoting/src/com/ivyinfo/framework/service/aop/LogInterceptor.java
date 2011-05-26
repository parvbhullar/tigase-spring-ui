package com.ivyinfo.framework.service.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.ivyinfo.framework.service.server.SpringContextUtil;

/**
 * Interception Around 环绕通知
 * @author Administrator
 *
 */
public class LogInterceptor implements MethodInterceptor {   
	private Logger logger =  Logger.getLogger("LogInterceptor");

    public Object invoke(MethodInvocation invocation) throws Throwable {  
    	long procTime = System.currentTimeMillis();
    	logger.log(Level.INFO, "执行："+invocation.getMethod() + " 方法.");
    	if(invocation.getArguments().length > 0){
    		logger.log(Level.INFO, "输入参数："+invocation.getArguments()[0]);
    	}
    	   	
//    	Object result = null;
//    	AIInterface AIInterface = (AIInterface)SpringContextUtil.getBean("AI");   
//        try{
//        	
//        	AIInterface.BeforeAI(invocation.getMethod().toString(), invocation.getArguments());
//        	
//        	result = invocation.proceed();  
//        	
//        	result = AIInterface.CurrentAI(invocation.getMethod().toString(), result);
//        	
//        	return result;
//        }finally{
//        	AIInterface.AfterAI(invocation.getMethod().toString(), result);
//        	
//        	procTime = System.currentTimeMillis() - procTime;
//        	logger.log(Level.DEBUG, "返回的结果为："+ result +" end 共用 ："+procTime+" 毫秒.");
//        }
    	return null;
    } 
}