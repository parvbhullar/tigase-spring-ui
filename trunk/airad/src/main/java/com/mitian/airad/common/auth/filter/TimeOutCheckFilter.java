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

import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.airad.common.auth.context.SecurityContextHolder;
import com.mitian.airad.common.auth.cookie.CookieGenerator;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.utils.MathUtils;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.utils.CookieUtils;

/**
 * 检查超时，默认超过一个小时不做任何操作，则让Cookie失效
 * 
 * @author zhoufengbo
 */
public class TimeOutCheckFilter extends AbstractAuthFilter {
    private long timeOutInMinute = 60L;
    private CookieGenerator cookieGenerator;
    private CookieGenerator lastVisitCookieGenerator;
    private boolean anonymousAccess = false;
    @Autowired
    private EncryptService encryptService;

    @Override
    protected void doInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        boolean hasTimeOut = hasTimeOut((HttpServletRequest) request, (HttpServletResponse) response);
        if (hasTimeOut) {
            processTimeOut(request, response, chain);
        }
        else {
            modifyLastVistTime((HttpServletResponse) response);
            chain.doFilter(request, response);
            return;
        }
    }

    /**
     * @param request
     * @param response
     * @return
     */
    protected boolean hasTimeOut(HttpServletRequest request, HttpServletResponse response) {
        boolean isTimeOut = false;

        if (hasSecurityContext()) {
            String strLastVisitTime = lastVisitCookieGenerator.retrieveCookieValue(request);
            if (StringUtils.isNotBlank(strLastVisitTime)) {
                try {
                    String lastVisit =
                            encryptService.decrypt(strLastVisitTime, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES
                                    .name());
                    long lastVisitTime = Long.parseLong(lastVisit);

                    if (MathUtils.abs((System.currentTimeMillis()) - lastVisitTime) >= timeOutInMinute * 60L * 1000L) {
                        isTimeOut = true;
                    }
                }
                catch (Exception ex) {
                    isTimeOut = true;
                }
            }
        }
        return isTimeOut;
    }

    /**
     * @return
     */
    private boolean hasSecurityContext() {
        return ((SecurityContextHolder.getContext() != null) && (SecurityContextHolder.getContext().getMemberInfo() != null));
    }

    /**
     * @param chain
     * @param response
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    protected void processTimeOut(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 清空上下文及所有Coookie
        SecurityContextHolder.clearContext();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        lastVisitCookieGenerator.removeCookie(httpServletResponse);
        cookieGenerator.removeCookie(httpServletResponse);
        CookieUtils.removeCookie(httpServletResponse, CookieUtils.COOKIE_KEY_COOKIE_ID);
        CookieUtils.removeCookie(httpServletResponse, CookieUtils.COOKIE_KEY_SESSION_ID);
        if (anonymousAccess) {
            // 跳转到BaseController处理
            chain.doFilter(request, response);
        }
        else {
            // 重新登录
            unsuccessfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
        }
    }

    /**
     * @param chain
     * @param response
     * @param request
     */
    private void modifyLastVistTime(HttpServletResponse response) {
        long curTime = System.currentTimeMillis();

        String encryptedCurTime =
                encryptService.encrypt("" + curTime, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());

        lastVisitCookieGenerator.addCookie(response, encryptedCurTime);

    }

    /**
     * @return the timeOutInMinute
     */
    public long getTimeOutInMinute() {
        return timeOutInMinute;
    }

    /**
     * @param timeOutInMinute the timeOutInMinute to set
     */
    public void setTimeOutInMinute(long timeOutInMinute) {
        this.timeOutInMinute = timeOutInMinute;
    }

    /**
     * @return the cookieGenerator
     */
    public CookieGenerator getCookieGenerator() {
        return cookieGenerator;
    }

    /**
     * @param cookieGenerator the cookieGenerator to set
     */
    public void setCookieGenerator(CookieGenerator cookieGenerator) {
        this.cookieGenerator = cookieGenerator;
    }

    /**
     * @return the lastVisitCookieGenerator
     */
    public CookieGenerator getLastVisitCookieGenerator() {
        return lastVisitCookieGenerator;
    }

    /**
     * @param lastVisitCookieGenerator the lastVisitCookieGenerator to set
     */
    public void setLastVisitCookieGenerator(CookieGenerator lastVisitCookieGenerator) {
        this.lastVisitCookieGenerator = lastVisitCookieGenerator;
    }

    /**
     * @return the anonymousAccess
     */
    public boolean isAnonymousAccess() {
        return anonymousAccess;
    }

    /**
     * @param anonymousAccess the anonymousAccess to set
     */
    public void setAnonymousAccess(boolean anonymousAccess) {
        this.anonymousAccess = anonymousAccess;
    }

}
