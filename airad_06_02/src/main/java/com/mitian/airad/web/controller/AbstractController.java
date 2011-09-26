package com.mitian.airad.web.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.common.auth.context.SecurityContext;
import com.mitian.airad.common.auth.context.SecurityContextHolder;
import com.mitian.airad.common.utils.IpUtil;
import com.mitian.airad.model.CoreMemberInfo;

/**
 * AbstractController.java
 * 
 * @author baojun
 */
public abstract class AbstractController {
    private final Log logger = LogFactory.getLog(getClass());

    public CoreMemberInfo getMemberInfo() {
        SecurityContext securityContext = getSecurityContext();
        if (securityContext != null) {
            return securityContext.getMemberInfo();
        }
        else {
            return null;
        }
    }

    public SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    /**
     * 得到登陆用户的id
     * 
     * @return
     */
    public Long getLogonMemberId() {
        Long logonMemberId = 0L;
        SecurityContext context = getSecurityContext();
        if (context != null) {
            CoreMemberInfo info = getMemberInfo();
            if (info != null) {
                logonMemberId = info.getMemberId();
            }
        }
        return logonMemberId;
    }

    /**
     * 得到登陆用户的邮箱地址
     * 
     * @return
     */
    public String getLogonEmail() {
        String logonEmail = null;
        SecurityContext context = getSecurityContext();
        if (context != null) {
            CoreMemberInfo info = getMemberInfo();
            if (info != null) {
                logonEmail = info.getEmail();
            }
        }
        return logonEmail;
    }
    private static final String LOG_TEMPLETE = "request info ip:{0}, memberId:{1}, logonEmail: {2}, url :{3}?{4}";

    /**
     * 自定义log输出
     * 
     * @param request
     * @return
     */
    public String toLogString(HttpServletRequest request) {
        return MessageFormat.format(LOG_TEMPLETE, IpUtil.getIpAddr(request), getLogonMemberId(), getLogonEmail(),
                request.getRequestURI(), request.getQueryString());
    }

    /**
     * 返回重定向ModelAndView
     * 
     * @param redirectURL
     * @return
     */
    public ModelAndView redirectView(String redirectURL) {
        if (!redirectURL.startsWith("/")) {
            redirectURL = "/" + redirectURL;
        }
        return new ModelAndView("redirect:" + redirectURL);
    }

    public ModelAndView forwardView(String forwardURl) {
        if (!forwardURl.startsWith("/")) {
            forwardURl = "/" + forwardURl;
        }
        return new ModelAndView("forward:" + forwardURl);
    }

    /**
     * @return the logger
     */
    public Log getLogger() {
        return logger;
    }
}
