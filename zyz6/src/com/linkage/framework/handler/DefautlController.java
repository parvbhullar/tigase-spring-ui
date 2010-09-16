package com.linkage.framework.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefautlController {

	@RequestMapping("/**")
	public ModelAndView defaultHandler(){
		System.out.println("***************************************************");
		System.out.println("**              这是缺省的处理器                                             **");
		System.out.println("***************************************************");
		return new ModelAndView("common/default","message","不能处理该请求！");
	}
}
