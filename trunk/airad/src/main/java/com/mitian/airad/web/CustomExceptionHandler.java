/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.web.controller.AbstractController;

/**
 * CustomExceptionHandler.java<br/>
 * 自定义spring MVC异常处理类，记录异常日志
 * 
 * @author baojun
 */
public class CustomExceptionHandler extends SimpleMappingExceptionResolver {
    private static final Logger log = Logger.getLogger(CustomExceptionHandler.class);

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.servlet.handler.SimpleMappingExceptionResolver#doResolveException(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            log.warn("handle with HttpRequestMethodNotSupportedException " + getRequestInfo(request, handler), ex);
            return new ModelAndView("forward:/errorDocs/404.jsp");
        }
        else if (ex instanceof InvalidInfoException) {
            log.warn("handle with InvalidInfoException " + getRequestInfo(request, handler), ex);
            return new ModelAndView("forward:/errorDocs/404.jsp");
        }
        else if (ex instanceof NotLogonException) {
            log.warn("handle with NotLogonException " + getRequestInfo(request, handler), ex);
            return new ModelAndView("redirect:/member.do?action=logon");
        }
        else {
            log.error("handle with unknowException " + getRequestInfo(request, handler), ex);
            return super.doResolveException(request, response, handler, ex);
        }
    }
    private final String LOG_TEMPLETE = " request info ip:{0}, url:{1}";

    /**
     * 记录请求信息
     * 
     * @param request
     * @param handler
     * @return
     */
    private String getRequestInfo(HttpServletRequest request, Object handler) {
        if (handler instanceof AbstractController) {
            AbstractController abstractController = (AbstractController) handler;
            return abstractController.toLogString(request);
        }
        else {
            return MessageFormat.format(LOG_TEMPLETE, request.getRemoteAddr(), request.getRequestURI()
                    + request.getQueryString());
        }
    }
}
