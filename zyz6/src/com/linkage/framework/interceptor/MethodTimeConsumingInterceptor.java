package com.linkage.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MethodTimeConsumingInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/*
		 * 由于这个方法是在渲染视图后运行，所以在该方法中设置request属性没有效果
		 */
		Long completionTime=System.currentTimeMillis();
		request.setAttribute("completionTime", completionTime);
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		Long postTime=System.currentTimeMillis();
		request.setAttribute("postTime", postTime);
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		Long preTime=System.currentTimeMillis();
		request.setAttribute("preTime", preTime);
//		return super.preHandle(request, response, handler);
		return true;
	}
	
	

}
