package org.njdt.gg.ccl.tplengine.velocity;

import org.njdt.gg.ccl.util.GlobalConstants;

/**
 * 初始模板引擎异常类
 * @author XiongChun
 * @since 2009-07-28
 * @see RuntimeException
 */
public class InitVelocityEngineException extends RuntimeException{
	
	/**
	 * 缺省串行版本标识
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造函数1
	 * @param 
	 */
	public InitVelocityEngineException(){
		super(GlobalConstants.Exception_Head + "初始化eRedG4平台缺省模板引擎失败.\n");
	}
	
	/**
	 * 构造函数2
	 * @param 
	 */
	public InitVelocityEngineException(String msg){
		super(GlobalConstants.Exception_Head + "初始化eRedG4平台缺省模板引擎失败\n" + msg);
	}

}
