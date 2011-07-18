package com;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ivyinfo.framework.service.server.SpringContextHolder;


public class ContextLoaderServlet extends   ContextLoaderListener   {
	private Logger log = Logger.getLogger("ContextLoaderServlet");

	public void init() throws ServletException {
//		System.err.println("==========into ContextLoaderServlet=========");
//		super.init();
//		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//		SpringContextHolder.setApplicationContext(context);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override  
	public void contextInitialized(ServletContextEvent event) {
		log.info("==========into ContextLoaderServlet=========");
		//super.init();
		super.contextInitialized(event);   
        ServletContext context = event.getServletContext();   
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);   
        //设置appliactionContext   
        SpringContextHolder.setApplicationContext(ctx);
		
	}

}
