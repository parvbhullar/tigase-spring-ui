/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.mitian.airad.common.auth.filter.processor.ExcludeUrlProcessor;
import com.mitian.airad.common.auth.filter.processor.IncludeUrlProcessor;
import com.mitian.airad.common.auth.filter.processor.LoginUrlProcessor;
import com.mitian.airad.common.utils.StringEscapeUtils;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.common.utils.UrlUtils;

/**
 * 抽象认证过滤器,只关注于路径匹配
 * 
 * @author zhoufengbo
 * @since 2010-10-4
 */
public abstract class AbstractAuthFilter extends GenericFilterBean {
    /** 忽略优先 */
    private boolean ignoreFirst;

    /** 未登录的转向页面 */
    private String nologinUrl;

    /** 无权限的转向页面，供特定拦截器使用 */
    private String nopermissionUrl;

    /**
     * 如果忽略优先,正则匹配可以忽略的URL
     */
    private ExcludeUrlProcessor excludeUrlProcessor;

    /**
     * 正则匹配需要处理的URL
     */
    private IncludeUrlProcessor includeUrlProcessor;

    /**
     * 正则匹配需要自定义登录页面的URL
     */
    private LoginUrlProcessor loginUrlProcessor;

    /**
     * 内部重定向 or 外部重定向
     */
    private boolean forwardToDestination = false;

    /**
     * 使用上下文相关路径
     */
    private boolean contextRelative = false;

    /**
     * 是否从登陆页面跳转到源
     */
    private boolean redirectToSource = false;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        String requestUrl = getFullRequestUrl(request);
        logger.debug("the request[" + requestUrl + "] come in the Auth Filter");
        if (ignoreFirst) {
            if (excludeUrlProcessor != null && excludeUrlProcessor.isExclude(getRequestUrl(request))) {
                chain.doFilter(request, response);
                return;
            }
        }
        if (includeUrlProcessor != null && !includeUrlProcessor.isinclude(getRequestUrl(request))) {
            chain.doFilter(request, response);
            return;
        }
        doInternal(request, response, chain);

    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // 缺省登录页面
        String loginUrl = getNologinUrl();

        // 自定义登陆页面
        if (loginUrlProcessor != null && loginUrlProcessor.isMeet(getRequestUrl(request))) {
            loginUrl = loginUrlProcessor.receiveLoginUrl(getRequestUrl(request));
        }

        if (StringUtils.isBlank(loginUrl)) {
            logger.debug("No failure URL set, sending 401 Unauthorized error");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "只有登录用户才能访问, 请您登录");
        }
        else {
            // 渲染源URL,登录后再次跳转过去
            if (redirectToSource) {
                loginUrl = calculateLoginUrl(request, loginUrl);
            }
            // ajax调用方法未登录处理
            String requestType = StringUtils.defaultString(request.getHeader("X-Requested-With"));
            if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
                response.getWriter().write("<script>window.location.href = \"" + loginUrl + "\";</script>");
            }
            else {
                if (forwardToDestination) {
                    logger.debug("Forwarding to " + loginUrl);
                    request.getRequestDispatcher(loginUrl).forward(request, response);
                }
                else {
                    logger.debug("Redirecting to " + loginUrl);
                    String redirectUrl = calculateRedirectUrl(request.getContextPath(), loginUrl);
                    redirectUrl = response.encodeRedirectURL(redirectUrl);
                    response.sendRedirect(redirectUrl);
                }
            }
        }
    }

    protected void unsuccessfulAuthorization(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String permissionUrl = getNopermissionUrl();
        if (StringUtils.isBlank(permissionUrl)) {
            logger.debug("No failure URL set, sending 401 Unauthorized error");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "您无权访问受限制的资源,请联系我们开通权限");
        }
        else {
            // ajax调用加有权限方法处理，请求头由js框架自动添加
            String requestType = StringUtils.defaultString(request.getHeader("X-Requested-With"));
            if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
                response.getWriter().write("<script>window.location.href = \"" + permissionUrl + "\";</script>");
            }
            else {
                if (forwardToDestination) {
                    logger.debug("Forwarding to " + permissionUrl);
                    request.getRequestDispatcher(permissionUrl).forward(request, response);
                }
                else {
                    logger.debug("Redirecting to " + permissionUrl);
                    String redirectUrl = calculateRedirectUrl(request.getContextPath(), permissionUrl);
                    redirectUrl = response.encodeRedirectURL(redirectUrl);
                    response.sendRedirect(redirectUrl);
                }
            }
        }
    }

    /**
     * @param request
     * @param loginUrl
     * @return
     */
    protected String calculateLoginUrl(HttpServletRequest request, String loginUrl) {
        String nextUrl = calculateNextUrl(request);
        if (StringUtils.isBlank(nextUrl)) {
            return loginUrl;
        }
        nextUrl = StringEscapeUtils.escapeURL(nextUrl);
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.contains(loginUrl, "?")) {
            return buffer.append(loginUrl).append("&").append("nextUrl=").append(nextUrl).toString();
        }
        else {
            return buffer.append(loginUrl).append("?").append("nextUrl=").append(nextUrl).toString();
        }
    }

    private String calculateNextUrl(HttpServletRequest request) {
        String nextUrl = null;
        if ("GET".equals(request.getMethod())) {
            nextUrl = getFullRequestUrl(request);
        }
        else {
            nextUrl = request.getHeader("REFERER");
        }
        return nextUrl;
    }

    protected String calculateRedirectUrl(String contextPath, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            if (contextRelative) {
                return url;
            }
            else {
                return contextPath + url;
            }
        }
        // Full URL, including http(s)://
        if (!contextRelative) {
            return url;
        }
        // Calculate the relative URL from the fully qualifed URL, minus the protocol and base context.
        url = url.substring(url.indexOf("://") + 3); // strip off protocol
        url = url.substring(url.indexOf(contextPath) + contextPath.length());

        if (url.length() > 1 && url.charAt(0) == '/') {
            url = url.substring(1);
        }
        return url;
    }

    protected abstract void doInternal(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException;

    public boolean isIgnoreFirst() {
        return ignoreFirst;
    }

    public void setIgnoreFirst(boolean ignoreFirst) {
        this.ignoreFirst = ignoreFirst;
    }

    public ExcludeUrlProcessor getExcludeUrlProcessor() {
        return excludeUrlProcessor;
    }

    public void setExcludeUrlProcessor(ExcludeUrlProcessor excludeUrlProcessor) {
        this.excludeUrlProcessor = excludeUrlProcessor;
    }

    public IncludeUrlProcessor getIncludeUrlProcessor() {
        return includeUrlProcessor;
    }

    public void setIncludeUrlProcessor(IncludeUrlProcessor includeUrlProcessor) {
        this.includeUrlProcessor = includeUrlProcessor;
    }

    public String getNologinUrl() {
        return nologinUrl;
    }

    public void setNologinUrl(String nologinUrl) {
        this.nologinUrl = nologinUrl;
    }

    public String getNopermissionUrl() {
        return nopermissionUrl;
    }

    public void setNopermissionUrl(String nopermissionUrl) {
        this.nopermissionUrl = nopermissionUrl;
    }

    /**
     * @return the contextRelative
     */
    public boolean isContextRelative() {
        return contextRelative;
    }

    /**
     * @param contextRelative the contextRelative to set
     */
    public void setContextRelative(boolean contextRelative) {
        this.contextRelative = contextRelative;
    }

    /**
     * @return the forwardToDestination
     */
    public boolean isForwardToDestination() {
        return forwardToDestination;
    }

    /**
     * @param forwardToDestination the forwardToDestination to set
     */
    public void setForwardToDestination(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }

    /**
     * @return the redirectToSource
     */
    public boolean isRedirectToSource() {
        return redirectToSource;
    }

    /**
     * @param redirectToSource the redirectToSource to set
     */
    public void setRedirectToSource(boolean redirectToSource) {
        this.redirectToSource = redirectToSource;
    }

    /**
     * @return the loginUrlProcessor
     */
    public LoginUrlProcessor getLoginUrlProcessor() {
        return loginUrlProcessor;
    }

    /**
     * @param loginUrlProcessor the loginUrlProcessor to set
     */
    public void setLoginUrlProcessor(LoginUrlProcessor loginUrlProcessor) {
        this.loginUrlProcessor = loginUrlProcessor;
    }

    /**
     * 解决POST提交问题
     * 
     * @param request
     * @return
     */
    protected String getRequestUrl(ServletRequest request) {
        String requestUrl = UrlUtils.buildRequestUrl((HttpServletRequest) request);
        StringBuffer buffer = new StringBuffer();
        buffer.append(requestUrl);
        Map<String, Object> actionMap = UrlUtils.getParameters(request);

        if (!StringUtils.containsIgnoreCase(requestUrl, "?action=")
                && !StringUtils.containsIgnoreCase(requestUrl, "&action=")) {
            // JS|CSS等不需要拼接action
            if (actionMap.get("action") != null) {
                if (StringUtils.contains(requestUrl, "?")) {
                    buffer.append("&action=").append(actionMap.get("action"));
                }
                else {
                    buffer.append("?action=").append(actionMap.get("action"));
                }
            }
        }
        return buffer.toString();
    }

    public String getFullRequestUrl(ServletRequest request) {
        return UrlUtils.buildFullRequestUrl((HttpServletRequest) request);
    }

}
