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

import com.mitian.airad.common.auth.context.SecurityContext;
import com.mitian.airad.common.auth.context.SecurityContextHolder;

/**
 * 安全检查过滤器,确保只有登录的用户才能访问受限制的资源
 * 
 * @author zhoufengbo
 */
public class SecurityCheckFilter extends AbstractAuthFilter {

    @Override
    protected void doInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        boolean hasLogin = hasLogin((HttpServletRequest) request, (HttpServletResponse) response);
        if (!hasLogin) {
            unsuccessfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
            return;
        }
        chain.doFilter(request, response);
        return;

    }

    /**
     * 验证Cookie是否有在登录时写入的UserId
     * 
     * @param response
     * @param request
     * @return
     */
    private boolean hasLogin(HttpServletRequest request, HttpServletResponse response) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return ((securityContext != null) && (securityContext.getMemberInfo() != null) && (securityContext
                .getMemberInfo().getLogonInfo() != null));

    }
}
