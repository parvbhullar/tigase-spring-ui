/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitian.airad.common.auth.context.SecurityContextHolder;
import com.mitian.airad.common.auth.cookie.CookieGenerator;

/**
 * 注销/登出 处理
 * 
 * @author zhoufengbo
 * @since 2010-10-23上午11:41:43
 */
public class LogOutFilter extends AbstractAuthFilter {
    private CookieGenerator cookieGenerator;
    private CookieGenerator lastVisitCookieGenerator;

    @Override
    protected void doInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        SecurityContextHolder.clearContext();
        lastVisitCookieGenerator.removeCookie((HttpServletResponse) response);
        cookieGenerator.removeCookie((HttpServletResponse) response);
        setRedirectToSource(false);
        unsuccessfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response);

    }

    /**
     * @param cookieGenerator the cookieGenerator to set
     */
    public void setCookieGenerator(CookieGenerator cookieGenerator) {
        this.cookieGenerator = cookieGenerator;
    }

    /**
     * @param lastVisitCookieGenerator the lastVisitCookieGenerator to set
     */
    public void setLastVisitCookieGenerator(CookieGenerator lastVisitCookieGenerator) {
        this.lastVisitCookieGenerator = lastVisitCookieGenerator;
    }

}
