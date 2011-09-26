/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * RestFilter.java 支持REST风格的URL 参见rest-servlet.xml，容器对"/rest/*"的URL请求进行拦截，此过滤器用于给REST风格的URL统一添加"/rest" 前缀，使得spring
 * mvc能够正常处理<br/>
 * 正常请求： http://127.0.0.1:8080/test/443 <br/>
 * 拦截器拦截后转发请求 http://127.0.0.1:8080/rest/test/443
 * 
 * @author baojun
 */
public class RestFilter extends OncePerRequestFilter {
    // 过滤URL请求中带 “.” 的动态请求
    public static final Pattern NOT_FORWARD_PATH_PATTERN = Pattern.compile(".*\\..{1,}$");

    public static final Pattern STATIC_HTML_PATH_PATTERN = Pattern.compile(".*html$");

    /*
     * (non-Javadoc)
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURL = request.getRequestURI();
        if (requestURL.equals("/")) {
            request.getRequestDispatcher("/rest/static/index").forward(request, response);
            return;
        }
        if (STATIC_HTML_PATH_PATTERN.matcher(requestURL).matches()
                && !StringUtils.containsIgnoreCase(requestURL, "/model/")
                && !StringUtils.containsIgnoreCase(requestURL, "/preview/")) {
            request.getRequestDispatcher("/rest/static" + requestURL).forward(request, response);
            return;
        }
        if (NOT_FORWARD_PATH_PATTERN.matcher(requestURL).matches()) {
            filterChain.doFilter(request, response);
            return;
        }
        else {
            request.getRequestDispatcher("/rest" + requestURL).forward(request, response);
            return;
        }
    }
}
