/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import com.mitian.airad.common.auth.context.OperationEnvironment;
import com.mitian.airad.common.auth.context.SecurityContext;
import com.mitian.airad.common.auth.context.SecurityContextHolder;
import com.mitian.airad.common.auth.context.UserRoleSecurityContext;
import com.mitian.airad.common.auth.cookie.CookieGenerator;
import com.mitian.airad.common.auth.filter.processor.AnonymousUrlProcessor;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.exception.OssLogonErrorException;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.service.DeploymentContextService;
import com.mitian.airad.service.LogonService;
import com.mitian.airad.utils.CookieUtils;

/**
 * 用户登录后,随之建立SecurityContext,供后续的过滤器或者Controller使用
 * 
 * @author zhoufengbo
 */
public class SecurityContextFilter extends AbstractAuthFilter {
    private CookieGenerator cookieGenerator;

    @Autowired
    private LogonService logonService;

    @Autowired
    private DeploymentContextService deploymentContextService;

    /**
     * 正则匹配需要匿名登录的URL
     */
    private AnonymousUrlProcessor anonymousUrlProcessor;

    @Autowired
    private EncryptService encryptService;

    @Override
    protected void doInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try {
            // SecurityContext创建
            create((HttpServletRequest) request, (HttpServletResponse) response);

            // 交出控制权
            chain.doFilter(request, response);
        }
        catch (NotLogonException ex) {
            // 异常处理,跳到登录页面OR匿名登录
            logger.warn("请求[" + getFullRequestUrl(request) + "]认证失败,原因" + ex.getMessage());
            processAuthenticationException(request, response, chain);
        }
        catch (OssLogonErrorException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "后台用户登录失败 :"
                    + ExceptionUtils.getFullStackTrace(e));
        }
        finally {
            // 从ThreadLocal中清除SecurityContext
            SecurityContextHolder.clearContext();
        }

    }

    /**
     * 异常处理,跳到登录页面OR匿名登录
     * 
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    private void processAuthenticationException(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 匿名登录处理
        if (anonymousUrlProcessor != null && anonymousUrlProcessor.isMeet(getRequestUrl(request))) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
        }
        else {
            unsuccessfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws NotLogonException,
            OssLogonErrorException {
        // 如果已经建立，直接返回
        if (SecurityContextHolder.getContext() != null) {
            return;
        }

        SecurityContext securityContext = new UserRoleSecurityContext();

        OperationEnvironment opEnv = getOpEnv(request, response);
        // 设置环境变量
        securityContext.setOperationEnvironment(opEnv);
        CoreMemberInfo coreMemberInfo = null;
        if (deploymentContextService.isOSSDeployType()) {
            coreMemberInfo = getMemberInfo4OssSales(request, response);
        }
        else {
            coreMemberInfo = getMemberInfo(request, response);
        }

        securityContext.setMemberInfo(coreMemberInfo);
        SecurityContextHolder.setContext(securityContext);

    }

    public static void main(String[] arg) throws UnsupportedEncodingException {
        String email = "22@dd.com";
        String s = URLEncoder.encode(email, "UTF-8");
        String s2 = HtmlUtils.htmlEscape(s);
        String s3 = HtmlUtils.htmlUnescape(s);
        System.out.println(s);
        System.out.println(s2);
        System.out.println(URLDecoder.decode(s, "UTF-8"));
    }

    public CoreMemberInfo getMemberInfo4OssSales(HttpServletRequest request, HttpServletResponse response)
            throws OssLogonErrorException {

        String saledIdStr = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_OSS_SALES_ID);
        String memberIdStr = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_OSS_MEMBER_ID);
        String salesEmail = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_OSS_EMAIL);
        if (StringUtils.isBlank(saledIdStr) || StringUtils.isBlank(memberIdStr)) {
            throw new OssLogonErrorException("后台销售人员未登陆");
        }
        String decodeSaleId =
                encryptService.decrypt(saledIdStr, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
        String decodeMemberId =
                encryptService.decrypt(memberIdStr, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());

        if (StringUtils.isBlank(decodeSaleId) || StringUtils.isBlank(decodeMemberId)) {
            throw new OssLogonErrorException("后台销售人员未登陆");
        }
        Long salesId = Long.valueOf(decodeSaleId);
        Long memberId = Long.valueOf(decodeMemberId);
        CoreMemberInfo coreMemberInfo = logonService.getLogonInfo4OssSalesLogon(salesId, memberId, salesEmail);
        return coreMemberInfo;
    }

    private CoreMemberInfo getMemberInfo(HttpServletRequest request, HttpServletResponse response)
            throws NotLogonException {
        String encryptCookieId = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_COOKIE_ID);
        String encryptSessionId = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_SESSION_ID);
        CoreMemberInfo coreMemberInfo =
                logonService.getLogonInfoByEncryptCookieIdAndEncryptSessionId(encryptCookieId, encryptSessionId);
        return coreMemberInfo;
    }

    /**
     * @return the anonymousUrlProcessor
     */
    public AnonymousUrlProcessor getAnonymousUrlProcessor() {
        return anonymousUrlProcessor;
    }

    /**
     * @param anonymousUrlProcessor the anonymousUrlProcessor to set
     */
    public void setAnonymousUrlProcessor(AnonymousUrlProcessor anonymousUrlProcessor) {
        this.anonymousUrlProcessor = anonymousUrlProcessor;
    }

    private OperationEnvironment getOpEnv(HttpServletRequest request, HttpServletResponse response) {
        OperationEnvironment opEnv = new OperationEnvironment();

        opEnv.setClientIp(request.getRemoteAddr());

        opEnv.setServerName(request.getServerName());

        opEnv.setTargetUrl(getFullRequestUrl(request));

        return opEnv;
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
}
