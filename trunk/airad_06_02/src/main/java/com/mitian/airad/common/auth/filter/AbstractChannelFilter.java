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

import org.apache.commons.lang.StringUtils;

import com.mitian.airad.common.auth.filter.processor.DefaultPortMapper;
import com.mitian.airad.common.auth.filter.processor.DefaultPortResolver;
import com.mitian.airad.common.auth.filter.processor.PortMapper;
import com.mitian.airad.common.auth.filter.processor.PortResolver;
import com.mitian.airad.common.utils.UrlUtils;

/**
 * 安全信道基类，目前分为Http和Https两种
 * 
 * @author zhoufengbo
 * @since 2010-11-12下午01:51:14
 */
public abstract class AbstractChannelFilter extends AbstractAuthFilter {

    private PortMapper portMapper = new DefaultPortMapper();
    private PortResolver portResolver = new DefaultPortResolver();

    @Override
    protected void doInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        boolean meet = isMeet((HttpServletRequest) request, (HttpServletResponse) response);
        if (meet) {
            unSecureChannel((HttpServletRequest) request, (HttpServletResponse) response);
            return;
        }
        chain.doFilter(request, response);
        return;
    }

    protected abstract boolean isMeet(HttpServletRequest request, HttpServletResponse response);

    private void unSecureChannel(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        Integer currentPort = Integer.valueOf(portResolver.getServerPort(request));
        Integer redirectPort = getMappedPort(currentPort);

        String redirectUrl =
                UrlUtils.buildFullRequestUrl(getScheme(), request.getServerName(), redirectPort, request
                        .getRequestURI(), request.getQueryString());

        if (logger.isDebugEnabled()) {
            logger.debug("Redirecting to: " + redirectUrl);
        }
        /**
         * 会员中心无刷新,判断是否AJAX请求：是则与member_center.js协作,否则直接跳转
         */
        String requestType = StringUtils.defaultString(request.getHeader("X-Requested-With"));
        if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
            response.getWriter().write("redirect:" + response.encodeRedirectURL(redirectUrl));
        }
        else {
            response.sendRedirect(response.encodeRedirectURL(redirectUrl));
        }
    }

    protected abstract Integer getMappedPort(Integer mapFromPort);

    /**
     * @return the portResolver
     */
    public PortResolver getPortResolver() {
        return portResolver;
    }

    /**
     * @param portResolver the portResolver to set
     */
    public void setPortResolver(PortResolver portResolver) {
        this.portResolver = portResolver;
    }

    /**
     * @return the portMapper
     */
    public PortMapper getPortMapper() {
        return portMapper;
    }

    /**
     * @param portMapper the portMapper to set
     */
    public void setPortMapper(PortMapper portMapper) {
        this.portMapper = portMapper;
    }

    /**
     * @return the scheme
     */
    protected abstract String getScheme();

    /**
     * @return the standardPort
     */
    protected abstract int getStandardPort();

}
