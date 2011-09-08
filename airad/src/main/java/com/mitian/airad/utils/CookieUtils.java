/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 日期操作工具类
 * 
 * @see apache-commons
 */
public class CookieUtils {
    // 存放提示信息的key
    private static final String COOKIE_KEY_TIP_MESSAGE = "_m";
    // 存放cookieId的key
    public static final String COOKIE_KEY_COOKIE_ID = "cid";
    // 存放sessionId的key
    public static final String COOKIE_KEY_SESSION_ID = "sid";
    // 存放用户nickname的key
    public static final String COOKIE_KEY_NICK_NAME = "nn";

    // 存放用户上一次访问时间的key
    public static final String COOKIE_KEY_LAST_VISIT_COOKIE_GENERATOR = "mtl";

    // 存放当前用户角色的key 对角色是开发者与广告商而言
    public static final String COOKIE_KEY_CURRENT_ROLE_TYPE = "ct";

    // OSS用户使用airad4oss项目所需cookie key
    public static final String COOKIE_KEY_OSS_SALES_ID = "salesid";
    public static final String COOKIE_KEY_OSS_MEMBER_ID = "memberid";
    public static final String COOKIE_KEY_OSS_EMAIL = "salesemail";

    /**
     * airad4oss登陆后写cookie
     * 
     * @param request
     * @param response
     * @param salesId
     * @param memberId
     */
    public static void setCookieAfterOssLogon(HttpServletResponse response, String salesId, String memberId,
            String encryptedCurTime) {
        setSessionCookie(response, COOKIE_KEY_OSS_SALES_ID, salesId);
        setSessionCookie(response, COOKIE_KEY_OSS_MEMBER_ID, memberId);
        setSessionCookie(response, COOKIE_KEY_OSS_EMAIL, "sales@airad.com");
        setSessionCookie(response, COOKIE_KEY_LAST_VISIT_COOKIE_GENERATOR, encryptedCurTime);
    }

    /**
     * 是否需要引导用户身份认证
     */
    private static final String COOKIE_AUTH_MESSAGE = "_adauth";

    private static Logger logger = Logger.getLogger(CookieUtils.class);

    /**
     * 设置页面操作提示信息，通过cookie携带，重定向后任然可访问
     * 
     * @param request
     * @param response
     * @param message
     */
    public static void setTipMessageCookie(HttpServletRequest request, HttpServletResponse response, String message) {
        try {
            Cookie cookie = new Cookie(COOKIE_KEY_TIP_MESSAGE, URLEncoder.encode(message, "utf-8"));
            // cookie存活时间为20秒
            cookie.setMaxAge(2 * 10);
            response.addCookie(cookie);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("setTipMessageCookie error  message: " + message);
        }
    }

    /**
     * 设置长cookie
     * 
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     */
    public static void setSessionCookie(HttpServletResponse response, String cookieKey, String cookieValue) {
        try {
            Cookie cookie = new Cookie(cookieKey, URLEncoder.encode(cookieValue, "utf-8"));
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("setLongCookie  error  cookieValue: " + cookieValue);
        }
    }

    public static void setAuthFlagCookie(HttpServletResponse response, String adId, String flag) {
        try {
            Cookie cookie = new Cookie(COOKIE_AUTH_MESSAGE + adId, URLEncoder.encode(flag, "utf-8"));
            // cookie存活时间为20秒
            cookie.setMaxAge(2 * 10);
            response.addCookie(cookie);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("setAuthMessageCookie error  message: " + flag);
        }
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        try {
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "utf-8");
                    }
                }
            }
        }
        catch (UnsupportedEncodingException e) {
            logger.error("setAuthMessageCookie error  cookie name: " + name, e);
        }
        return null;
    }

    /**
     * cookie删除
     * 
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
