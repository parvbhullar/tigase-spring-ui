package org.eredlab.g4.arm.util.idgenerator;

import org.njdt.gg.ccl.id.generator.DefaultIDGenerator;

/**
 * ID生成器 静态类解决多线程并发访问生成ID的问题
 * 此类第一次实例化会执行所有的static代码块，如果想按需加载这些ID生成器，则应该一个ID写一个静态类就可以
 * 
* @author njdt
 * @since 2010-09-16s
 */
public class IDHelper {

	/**
	 * 事件跟踪ID
	 */
	private static DefaultIDGenerator defaultIDGenerator_eventid = null;

	/**
	 * SpringBean监控ID
	 */
	private static DefaultIDGenerator defaultIDGenerator_monitorid = null;

	/**
	 * 项目ID(测试表)
	 */
	private static DefaultIDGenerator defaultIDGenerator_xmid = null;

	/**
	 * CODEID
	 */
	private static DefaultIDGenerator defaultIDGenerator_codeid = null;

	/**
	 * EXCEPTIONID
	 */
	private static DefaultIDGenerator defaultIDGenerator_exceptionid = null;
	
	/**
	 * AUTHORIZEID_ROLE
	 */
	private static DefaultIDGenerator defaultIDGenerator_authorizeid_role = null;
	
	/**
	 * PARAMID
	 */
	private static DefaultIDGenerator defaultIDGenerator_paramid = null;
	
	/**
	 * ROLEID
	 */
	private static DefaultIDGenerator defaultIDGenerator_roleid = null;
	
	/**
	 * AUTHORIZEID_USERMENUMAP
	 */
	private static DefaultIDGenerator defaultIDGenerator_authorizeid_usermenumap = null;
	
	/**
	 * AUTHORIZEID_USER
	 */
	private static DefaultIDGenerator defaultIDGenerator_authorizeid_user = null;
	
	/**
	 * USERID
	 */
	private static DefaultIDGenerator defaultIDGenerator_userid = null;
	
	/**
	 * FILEID
	 */
	private static DefaultIDGenerator defaultIDGenerator_fileid = null;
	
	
	static {
		IdGenerator idGenerator_eventid = new IdGenerator();
		idGenerator_eventid.setFieldname("EVENTID");
		defaultIDGenerator_eventid = idGenerator_eventid.getDefaultIDGenerator();
	}

	static {
		IdGenerator idGenerator_monitorid = new IdGenerator();
		idGenerator_monitorid.setFieldname("MONITORID");
		defaultIDGenerator_monitorid = idGenerator_monitorid.getDefaultIDGenerator();
	}

	static {
		IdGenerator idGenerator_xmid = new IdGenerator();
		idGenerator_xmid.setFieldname("XMID");
		defaultIDGenerator_xmid = idGenerator_xmid.getDefaultIDGenerator();
	}

	static {
		IdGenerator idGenerator_codeid = new IdGenerator();
		idGenerator_codeid.setFieldname("CODEID");
		defaultIDGenerator_codeid = idGenerator_codeid.getDefaultIDGenerator();
	}

	static {
		IdGenerator idGenerator_exceptionid = new IdGenerator();
		idGenerator_exceptionid.setFieldname("EXCEPTIONID");
		defaultIDGenerator_exceptionid = idGenerator_exceptionid.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_authorizeid_role = new IdGenerator();
		idGenerator_authorizeid_role.setFieldname("AUTHORIZEID_ROLE");
		defaultIDGenerator_authorizeid_role = idGenerator_authorizeid_role.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_paramid = new IdGenerator();
		idGenerator_paramid.setFieldname("PARAMID");
		defaultIDGenerator_paramid = idGenerator_paramid.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_roleid = new IdGenerator();
		idGenerator_roleid.setFieldname("ROLEID");
		defaultIDGenerator_roleid = idGenerator_roleid.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_authorizeid_usermenumap = new IdGenerator();
		idGenerator_authorizeid_usermenumap.setFieldname("AUTHORIZEID_USERMENUMAP");
		defaultIDGenerator_authorizeid_usermenumap = idGenerator_authorizeid_usermenumap.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_authorizeid_user = new IdGenerator();
		idGenerator_authorizeid_user.setFieldname("AUTHORIZEID_USER");
		defaultIDGenerator_authorizeid_user = idGenerator_authorizeid_user.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_userid = new IdGenerator();
		idGenerator_userid.setFieldname("USERID");
		defaultIDGenerator_userid = idGenerator_userid.getDefaultIDGenerator();
	}
	
	static {
		IdGenerator idGenerator_fileid = new IdGenerator();
		idGenerator_fileid.setFieldname("FILEID");
		defaultIDGenerator_fileid = idGenerator_fileid.getDefaultIDGenerator();
	}

	/**
	 * 返回事件跟踪ID
	 * 
	 * @return
	 */
	public static String getEventID() {
		return defaultIDGenerator_eventid.create();
	}

	/**
	 * 返回SpringBean监控ID
	 * 
	 * @return
	 */
	public static String getMonitorID() {
		return defaultIDGenerator_monitorid.create();
	}

	/**
	 * 返回项目ID
	 * 
	 * @return
	 */
	public static String getXmID() {
		return defaultIDGenerator_xmid.create();
	}

	/**
	 * 返回CODEID
	 * 
	 * @return
	 */
	public static String getCodeID() {
		return defaultIDGenerator_codeid.create();
	}
	
	/**
	 * 返回ExceptionID
	 * 
	 * @return
	 */
	public static String getExceptionID() {
		return defaultIDGenerator_exceptionid.create();
	}
	
	/**
	 * 返回AUTHORIZEID_ROLE
	 * 
	 * @return
	 */
	public static String getAuthorizeid4Role() {
		return defaultIDGenerator_authorizeid_role.create();
	}
	
	/**
	 * 返回PARAMID
	 * 
	 * @return
	 */
	public static String getParamID() {
		return defaultIDGenerator_paramid.create();
	}
	
	/**
	 * 返回ROLEID
	 * 
	 * @return
	 */
	public static String getRoleID() {
		return defaultIDGenerator_roleid.create();
	}
	
	/**
	 * 返回AUTHORIZEID_USERMENUMAP
	 * 
	 * @return
	 */
	public static String getAuthorizeid4Usermenumap() {
		return defaultIDGenerator_authorizeid_usermenumap.create();
	}
	
	/**
	 * 返回AUTHORIZEID_USER
	 * 
	 * @return
	 */
	public static String getAuthorizeid4User() {
		return defaultIDGenerator_authorizeid_user.create();
	}
	
	/**
	 * 返回USERID
	 * 
	 * @return
	 */
	public static String getUserID() {
		return defaultIDGenerator_userid.create();
	}
	
	/**
	 * 返回FILEID
	 * 
	 * @return
	 */
	public static String getFileID() {
		return defaultIDGenerator_fileid.create();
	}
}
