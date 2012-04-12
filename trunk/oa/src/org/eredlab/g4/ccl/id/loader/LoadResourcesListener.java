package org.eredlab.g4.ccl.id.loader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eredlab.g4.ccl.id.IDException;

/**
 * LoadResourcesListener
 * 此代码源于开源项目E3,原作者：黄云辉
 * 
 * @author XiongChun
 * @since 2010-03-17
 * @see IDException
 */
public class LoadResourcesListener implements ServletContextListener {
    
    
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		final String WEB_HOME = pServletContextEvent.getServletContext().getRealPath("/");
		ResourcesLoader.load(WEB_HOME);
	}

	public void contextDestroyed(ServletContextEvent pServletContextEvent) {
	}

}
