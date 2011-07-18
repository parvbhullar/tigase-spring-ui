package com.ivyinfo.framework.service.aop;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

//import com.opensymphony.xwork2.ActionInvocation;
//import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionTransactionInterceptor {//extends AbstractInterceptor {

	//会导致回滚的异常名（简单名）列表,在struts配置文件中定义，用逗号分割
	//在action中调用的任何一个service方法抛出exceptionList中定义的异常，则导致整个action回滚
	private String exceptionList; 
	private PlatformTransactionManager transactionManager;
	
//	@Override
//	public String intercept(ActionInvocation invocation) throws Exception {
//		// TODO Auto-generated method stub
//		String result = "error";
//		
//		TransactionDefinition def = new DefaultTransactionDefinition();
//		TransactionStatus txStatus = transactionManager.getTransaction(def);
//
//		try{
//			result = invocation.invoke();
//		}catch(Exception t){
//			
//			String className = t.getClass().toString();
//			String exceptionName = className.substring(className.lastIndexOf(".")+1);
//			
//			if(causeRollBack(exceptionName)){
//				transactionManager.rollback(txStatus);
//			}
//			else{
//				transactionManager.commit(txStatus);
//				
//			}
////			return "exception";
//			throw t;
//		}
//		
//		transactionManager.commit(txStatus);
//		return result;
//	}
//
//
//	private boolean causeRollBack(String exceptionName){
//		
//		if(exceptionList != null){
//			String[] exceptionArray = exceptionList.split(",");
//			for(int i=0;i<exceptionArray.length;i++){
//				
//				if(exceptionName.equals(exceptionArray[i])){
//					return true;
//				}
//			}
//		}
//			
//		return false;
//	}
//	
//	
//	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}


	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}


	public String getExceptionList() {
		return exceptionList;
	}
	
	public void setExceptionList(String exceptionList) {
		this.exceptionList = exceptionList;
	}

}
