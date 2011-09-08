/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

/**
 * Url工具类，从HttpServletRequest中构建访问URL
 * 
 * @author zhoufengbo
 * @see Spring-utils
 */
public class UrlUtils {
    public static String buildFullRequestUrl(HttpServletRequest r) {
        return buildFullRequestUrl(r.getScheme(), r.getServerName(), r.getServerPort(), r.getRequestURI(), r
                .getQueryString());
    }

    /**
     * Obtains the full URL the client used to make the request.
     * <p>
     * Note that the server port will not be shown if it is the default server port for HTTP or HTTPS (80 and 443
     * respectively).
     * 
     * @return the full URL, suitable for redirects (not decoded).
     */
    public static String buildFullRequestUrl(String scheme, String serverName, int serverPort, String requestURI,
            String queryString) {

        scheme = scheme.toLowerCase();

        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        // Only add port if not default
        if ("http".equals(scheme)) {
            if (serverPort != 80) {
                url.append(":").append(serverPort);
            }
        }
        else if ("https".equals(scheme)) {
            if (serverPort != 443) {
                url.append(":").append(serverPort);
            }
        }

        // Use the requestURI as it is encoded (RFC 3986) and hence suitable for redirects.
        url.append(requestURI);

        if (queryString != null) {
            url.append("?").append(queryString);
        }

        return url.toString();
    }

    /**
     * Obtains the web application-specific fragment of the request URL.
     * <p>
     * Under normal spec conditions,
     * 
     * <pre>
     * requestURI = contextPath + servletPath + pathInfo
     * </pre>
     * 
     * But the requestURI is not decoded, whereas the servletPath and pathInfo are (SEC-1255). This method is typically
     * used to return a URL for matching against secured paths, hence the decoded form is used in preference to the
     * requestURI for building the returned value. But this method may also be called using dummy request objects which
     * just have the requestURI and contextPatth set, for example, so it will fall back to using those.
     * 
     * @return the decoded URL, excluding any server name, context path or servlet path
     */
    public static String buildRequestUrl(HttpServletRequest r) {
        return buildRequestUrl(r.getServletPath(), r.getRequestURI(), r.getContextPath(), r.getPathInfo(), r
                .getQueryString());
    }

    /**
     * Obtains the web application-specific fragment of the URL.
     */
    private static String buildRequestUrl(String servletPath, String requestURI, String contextPath, String pathInfo,
            String queryString) {

        StringBuilder url = new StringBuilder();

        if (servletPath != null) {
            url.append(servletPath);
            if (pathInfo != null) {
                url.append(pathInfo);
            }
        }
        else {
            url.append(requestURI.substring(contextPath.length()));
        }

        if (queryString != null) {
            url.append("?").append(queryString);
        }

        return url.toString();
    }

    public static Map<String, Object> getParameters(ServletRequest request) {
        return getParametersStartingWith(request, null);

    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Assert.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);

                if (values != null && values.length > 0) {
                    if (values.length > 1) {
                        params.put(unprefixed, values);
                    }
                    else {
                        params.put(unprefixed, values[0]);
                    }
                }
            }
        }
        return params;
    }

    /**
     * Returns true if the supplied URL starts with a "/" or "http".
     */
    public static boolean isValidRedirectUrl(String url) {
        return url != null && (url.startsWith("/") || url.toLowerCase().startsWith("http"));
    }

}
