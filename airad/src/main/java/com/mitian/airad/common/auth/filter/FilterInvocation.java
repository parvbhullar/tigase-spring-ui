/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitian.airad.common.utils.UrlUtils;

/**
 * 保持 Http Filter上下文对象关联
 * 
 * @author zhoufengbo
 */
public class FilterInvocation {
    private FilterChain chain;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public FilterInvocation(ServletRequest request, ServletResponse response, FilterChain chain) {
        if ((request == null) || (response == null) || (chain == null)) {
            throw new IllegalArgumentException("Cannot pass null values to constructor");
        }

        this.request = (HttpServletRequest) request;
        this.response = (HttpServletResponse) response;
        this.chain = chain;
    }

    public FilterChain getChain() {
        return chain;
    }

    public String getFullRequestUrl() {
        return UrlUtils.buildFullRequestUrl(request);
    }

    public HttpServletRequest getHttpRequest() {
        return request;
    }

    public HttpServletResponse getHttpResponse() {
        return response;
    }

    public String getRequestUrl() {
        return UrlUtils.buildRequestUrl(request);
    }

    public HttpServletRequest getRequest() {
        return getHttpRequest();
    }

    public HttpServletResponse getResponse() {
        return getHttpResponse();
    }

    @Override
    public String toString() {
        return "FilterInvocation: URL: " + getRequestUrl();
    }

}
