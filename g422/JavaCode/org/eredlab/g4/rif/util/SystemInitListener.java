package org.eredlab.g4.rif.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.properties.PropertiesFactory;
import org.eredlab.g4.ccl.properties.PropertiesFile;
import org.eredlab.g4.ccl.properties.PropertiesHelper;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.util.G4Utils;
import org.njdt.gg.ccl.util.GlobalConstants;
import org.springframework.context.ApplicationContext;

/**
 * 系统启动监听器
 * 
* @author njdt
 * @since 2010-04-13
 */
public class SystemInitListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(SystemInitListener.class);
	private boolean success = true;
	private ApplicationContext wac = null;

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		systemStartup(sce.getServletContext());
	}

	/**
	 * 应用平台启动
	 */
	private void systemStartup(ServletContext servletContext) {
		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
		String forceLoad = pHelper.getValue("forceLoad", ArmConstants.FORCELOAD_N);
		long start = System.currentTimeMillis();
		if (forceLoad.equalsIgnoreCase(ArmConstants.FORCELOAD_N)) {
			System.out.println("********************************************");
			System.out.println("信息系统集成与应用开发平台[eRedG4]开始启动...");
			System.out.println("********************************************");
		}
		try {
			wac = SpringBeanLoader.getApplicationContext();
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		if (success) {
			MonitorService monitorService = (MonitorService) SpringBeanLoader.getSpringBean("monitorService");
			monitorService.deleteHttpSession(new BaseDto());
			try {
				initDbType();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (success) {
			System.out.println("-------------------------------");
			System.out.println("系统开始启动字典装载程序...");
			System.out.println("开始加载字典...");
			IReader g4Reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
			List codeList = null;
			try {
				codeList = g4Reader.queryForList("getCodeViewList");
				System.out.println("字典加载成功!");
			} catch (Exception e) {
				success = false;
				System.out.println("字典加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EACODELIST", codeList);
		}
		if (success) {
			System.out.println("-------------------------------");
			System.out.println("系统开始启动全局参数表装载程序...");
			System.out.println("开始加载全局参数表...");
			List paramList = null;
			try {
				IReader g4Reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
				paramList = g4Reader.queryForList("getParamList");
				System.out.println("全局参数表加载成功!");
			} catch (Exception e) {
				success = false;
				System.out.println("全局参数表加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EAPARAMLIST", paramList);
		}
		long timeSec = (System.currentTimeMillis() - start) / 1000;
		System.out.println("********************************************");
		if (success) {
			System.out.println("信息系统集成与应用开发平台[eRedG4]启动成功[" + G4Utils.getCurrentTime() + "]");
			System.out.println("启动总耗时: " + timeSec / 60 + "分 " + timeSec % 60 + "秒 ");
		} else {
			System.out.println("信息系统集成与应用开发平台[eRedG4]启动失败[" + G4Utils.getCurrentTime() + "]");
			System.out.println("启动总耗时: " + timeSec / 60 + "分" + timeSec % 60 + "秒");
		}
		System.out.println("********************************************");
	}

	/**
	 * 识别JDBC驱动类型
	 * 
	 * @throws SQLException
	 */
	private void initDbType() throws SQLException {
		IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
		Connection connection = g4Dao.getConnection();
		if (connection.getMetaData().getDatabaseProductName().toLowerCase().indexOf("ora") > -1) {
			System.setProperty("eRedg4.JdbcType", "oracle");
		} else if (connection.getMetaData().getDatabaseProductName().toLowerCase().indexOf("mysql") > -1) {
			System.setProperty("eRedg4.JdbcType", "mysql");
		} else {
			if (log.isErrorEnabled()) {
				log.error(GlobalConstants.Exception_Head + "G4平台目前还不支持你使用的数据库产品.如需获得支持,请和我们联系!");
			}
			System.exit(0);
		}
	}
}
