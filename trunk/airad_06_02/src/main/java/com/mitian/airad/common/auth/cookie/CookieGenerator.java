/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Cookie生成辅助类
 * 
 * @author zhoufengbo
 */
public class CookieGenerator {
    /**
     * Coookie默认写到根路径下.
     */
    public static final String DEFAULT_COOKIE_PATH = "/";

    private static final Log LOGGER = LogFactory.getLog(CookieGenerator.class);

    private String cookieName;

    private String cookieDomain;

    private String cookiePath = DEFAULT_COOKIE_PATH;

    private Integer cookieMaxAge = null;

    private boolean cookieSecure = false;

    /**
     * Use the given name for cookies created by this generator.
     */
    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    /**
     * Return the given name for cookies created by this generator.
     */
    public String getCookieName() {
        return cookieName;
    }

    /**
     * Use the given domain for cookies created by this generator. The cookie is only visible to servers in this domain.
     */
    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    /**
     * Return the domain for cookies created by this generator, if any.
     */
    public String getCookieDomain() {
        return cookieDomain;
    }

    /**
     * Use the given path for cookies created by this generator. The cookie is only visible to URLs in this path and
     * below.
     */
    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    /**
     * Return the path for cookies created by this generator.
     */
    public String getCookiePath() {
        return cookiePath;
    }

    /**
     * Use the given maximum age (in seconds) for cookies created by this generator. Useful special value: -1 ... not
     * persistent, deleted when client shuts down
     */
    public void setCookieMaxAge(Integer cookieMaxAge) {
        this.cookieMaxAge = cookieMaxAge;
    }

    /**
     * Return the maximum age for cookies created by this generator.
     */
    public Integer getCookieMaxAge() {
        return cookieMaxAge;
    }

    /**
     * Set whether the cookie should only be sent using a secure protocol, such as HTTPS (SSL). This is an indication to
     * the receiving browser, not processed by the HTTP server itself. Default is "false".
     */
    public void setCookieSecure(boolean cookieSecure) {
        this.cookieSecure = cookieSecure;
    }

    /**
     * Return whether the cookie should only be sent using a secure protocol, such as HTTPS (SSL).
     */
    public boolean isCookieSecure() {
        return cookieSecure;
    }

    /**
     * Add a cookie with the given value to the response, using the cookie descriptor settings of this generator.
     * <p>
     * Delegates to {@link #createCookie} for cookie creation.
     * 
     * @param response the HTTP response to add the cookie to
     * @param cookieValue the value of the cookie to add
     * @see #setCookieName
     * @see #setCookieDomain
     * @see #setCookiePath
     * @see #setCookieMaxAge
     */
    public void addCookie(HttpServletResponse response, String cookieValue) {
        Cookie cookie = createCookie(cookieValue);
        Integer maxAge = getCookieMaxAge();
        if (maxAge != null) {
            cookie.setMaxAge(maxAge);
        }
        if (isCookieSecure()) {
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added cookie with name [" + getCookieName() + "] and value [" + cookieValue + "]");
        }
    }

    /**
     * Remove the cookie that this generator describes from the response. Will generate a cookie with empty value and
     * max age 0.
     * <p>
     * Delegates to {@link #createCookie} for cookie creation.
     * 
     * @param response the HTTP response to remove the cookie from
     * @see #setCookieName
     * @see #setCookieDomain
     * @see #setCookiePath
     */
    public void removeCookie(HttpServletResponse response) {
        Cookie cookie = createCookie("");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Removed cookie with name [" + getCookieName() + "]");
        }
    }

    /**
     * Create a cookie with the given value, using the cookie descriptor settings of this generator (except for
     * "cookieMaxAge").
     * 
     * @param cookieValue the value of the cookie to crate
     * @return the cookie
     * @see #setCookieName
     * @see #setCookieDomain
     * @see #setCookiePath
     */
    protected Cookie createCookie(String cookieValue) {
        Cookie cookie = new Cookie(getCookieName(), cookieValue);
        if (getCookieDomain() != null) {
            cookie.setDomain(getCookieDomain());
        }
        cookie.setPath(getCookiePath());
        return cookie;
    }

    /**
     * Retrieve the Cookie Value
     * 
     * @param request
     * @return
     */
    public String retrieveCookieValue(final HttpServletRequest request) {
        final Cookie cookie = getCookie(request, getCookieName());

        return cookie == null ? null : cookie.getValue();
    }

    /**
     * Retrieve the first cookie with the given name. Note that multiple cookies can have the same name but different
     * paths or domains.
     * 
     * @param request current servlet request
     * @param name cookie name
     * @return the first cookie with the given name, or <code>null</code> if none is found
     */
    protected Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
