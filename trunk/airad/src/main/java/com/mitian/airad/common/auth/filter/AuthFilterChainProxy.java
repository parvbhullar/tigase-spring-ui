/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 过滤链代理,管理一系列过滤器
 * 
 * @author zhoufengbo
 */
public class AuthFilterChainProxy extends GenericFilterBean {
    private static final Log logger = LogFactory.getLog(AuthFilterChainProxy.class);

    private List<Filter> filters = Collections.emptyList();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        List<Filter> filters = getFilters();

        if (filters == null || filters.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug(fi.getRequestUrl() + " has no matching filters or has an empty filter list");
            }

            chain.doFilter(request, response);

            return;
        }

        VirtualFilterChain virtualFilterChain = new VirtualFilterChain(fi, filters);
        virtualFilterChain.doFilter(fi.getRequest(), fi.getResponse());
    }

    public List<Filter> getFilters() {
        return filters;

    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    /**
     * 过滤链实现,推动过滤器向下执行
     * 
     * @author zhoufengbo
     */
    private static class VirtualFilterChain implements FilterChain {
        private FilterInvocation fi;
        private List<Filter> additionalFilters;
        private int currentPosition = 0;

        private VirtualFilterChain(FilterInvocation filterInvocation, List<Filter> additionalFilters) {
            fi = filterInvocation;
            this.additionalFilters = additionalFilters;
        }

        public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
            if (currentPosition == additionalFilters.size()) {
                if (logger.isDebugEnabled()) {
                    logger.debug(fi.getRequestUrl()
                            + " reached end of additional filter chain; proceeding with original chain");
                }
                fi.getChain().doFilter(request, response);
            }
            else {
                currentPosition++;

                Filter nextFilter = additionalFilters.get(currentPosition - 1);

                if (logger.isDebugEnabled()) {
                    logger.debug(fi.getRequestUrl() + " at position " + currentPosition + " of "
                            + additionalFilters.size() + " in additional filter chain; firing Filter: '" + nextFilter
                            + "'");
                }
                nextFilter.doFilter(request, response, this);
            }
        }
    }

}
