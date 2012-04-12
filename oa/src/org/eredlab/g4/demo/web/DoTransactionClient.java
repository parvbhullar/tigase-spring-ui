package org.eredlab.g4.demo.web;

import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.demo.service.DemoService;
import org.eredlab.g4.rif.web.BaseAction;

public class DoTransactionClient {

	/**
	 * 演示事务控制
	 * 
	 * @author XiongChun
	 * @since 2011-2-30
	 * @see BaseAction
	 */
	public static void main(String[] args) {
		DemoService demoService = (DemoService)SpringBeanLoader.getSpringBean("demoService");
		Dto outDto = demoService.doTransactionTest();
		System.out.println("返回值:\n" + outDto);
	}

}
