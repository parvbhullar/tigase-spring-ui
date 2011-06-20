package org.eredlab.g4.rif.web;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;

/**
 * ActionServlet基类
 * @author XiongChun
 * @since 2009-09-03
 * @see ActionServlet
 */
public class BaseActionServlet extends ActionServlet{
	public BaseActionServlet(){}
	
	/**
     * @param 
     * @return void
	 */
	public void init() throws ServletException{
		super.init();
	}
}
