/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.auth.context.SecurityContextHolder;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.exception.OssLogonErrorException;
import com.mitian.airad.common.utils.DateFormatUtils;
import com.mitian.airad.common.utils.IpUtil;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.component.mail.Email;
import com.mitian.airad.component.mail.MailComponent;
import com.mitian.airad.dao.CoreMemberInfoDAO;
import com.mitian.airad.dao.CoreUserHisDAO;
import com.mitian.airad.dao.LogonInfoDao;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreUserHis;
import com.mitian.airad.model.LogonInfo;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.auth.roles.RoleNotLogon;

/**
 * LogonService.java
 * 
 * @author baojun
 */
@Service
public class LogonService {
    @Autowired
    private LogonInfoDao logonInfoDao;
    @Autowired
    private CoreMemberInfoDAO coreMemberInfoDAO;
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private MailComponent mailComponent;
    @Autowired
    private CoreUserHisDAO coreUserHisDAO;

    private static final String LOGON_MANY_TIMES_WARNING_INFO_TEMPLETE =
            "the logonEmail: {0},  try {1} times logon at time:{2} with  ip: {3} ,error password:{4},error captcha:{5} ,right captcha:{6}";

    private static final String LOGON_MANY_TIMES_WARNING_TITLE = "WARNING SOMEONE try too many times logon!";

    /**
     * 当有任一账号尝试登录5次失败后发送提醒邮件 预警
     */
    public void sendNoticeMail4TryLogonOver5Times(String logonEmail, int times, String ip, String errorPassword,
            String errorCaptcha, String rightCaptcha) {
        String emailText =
                MessageFormat.format(LOGON_MANY_TIMES_WARNING_INFO_TEMPLETE, logonEmail, times, DateFormatUtils.format(
                        Calendar.getInstance(), "yyyy-MM-dd"), ip, errorPassword, errorCaptcha, rightCaptcha);
        Email email =
                new Email("baojun@airad.com", Email.DEFAULT_FROM_ADDRESS, LOGON_MANY_TIMES_WARNING_TITLE, emailText,
                        true);
        mailComponent.sendSingleMail(email);
    }

    /**
     * 清理登录上下文
     * 
     * @param request
     * @param response
     */
    public void cleanLogonContext(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_COOKIE_ID);
        CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_SESSION_ID);
    }

    /**
     * 退出
     * 
     * @param logonEmail
     * @param password
     * @param request
     * @param response
     */
    public void txDoLogout(HttpServletRequest request, HttpServletResponse response) {
        String encryptCookieId = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_COOKIE_ID);
        String encryptSessionId = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_SESSION_ID);
        if (!StringUtils.isBlank(encryptCookieId) && !StringUtils.isBlank(encryptSessionId)) {
            String cookieId = decodeCookieValue(encryptCookieId);
            String sessionId = decodeCookieValue(encryptSessionId);
            logonInfoDao.deleteByCookieIdAndSessionId(cookieId, sessionId);
        }
        SecurityContextHolder.clearContext();
        CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_COOKIE_ID);
        CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_SESSION_ID);
    }

    /**
     * 会员登录 重置sessionId与cookieId
     * 
     * @param logonEmail
     * @param password
     * @param request
     * @param response
     * @return
     */
    public CoreMemberInfo txDoLogon(String logonEmail, String password, HttpServletRequest request,
            HttpServletResponse response) {
        LogonInfo logonInfo = new LogonInfo();
        CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(logonEmail);
        if (coreMemberInfo == null) {
            logonInfo.setLogonFailTipMessage("用户名或密码错误");
            CoreMemberInfo logonFailMemberInfo = new CoreMemberInfo();
            logonFailMemberInfo.setLogonInfo(logonInfo);
            return logonFailMemberInfo;
        }
        String md5Password = coreMemberInfo.getPassword();
        boolean match = encryptService.matches(password, md5Password, EncryptService.DIGEST_ALGORITHM.MD5.name());
        if (!match) {
            logonInfo.setLogonFailTipMessage("用户名或密码错误");
            CoreMemberInfo passwordErrorMemberInfo = new CoreMemberInfo();
            passwordErrorMemberInfo.setLogonInfo(logonInfo);
            return passwordErrorMemberInfo;
        }
        // 保存cookie 到客户端
        LogonInfo successLogonInfo = createLogonInfo(request, response, logonEmail, coreMemberInfo.getMemberId());
        coreMemberInfo.setLogonInfo(successLogonInfo);
        return coreMemberInfo;
    }

    /**
     * 会员登录 重置sessionId与cookieId
     * 
     * @param logonEmail
     * @param password
     * @param request
     * @param response
     * @return
     */
    public CoreMemberInfo txDoOnceLogon(CoreMemberInfo coreMemberInfo, HttpServletRequest request,
            HttpServletResponse response) {
        LogonInfo successLogonInfo =
                createLogonInfo(request, response, coreMemberInfo.getEmail(), coreMemberInfo.getMemberId());
        coreMemberInfo.setLogonInfo(successLogonInfo);
        return coreMemberInfo;
    }

    /**
     * 后台销售人员登录入口
     * 
     * @param salesId
     * @param memberId
     * @return
     * @throws OssLogonErrorException
     */
    public CoreMemberInfo getLogonInfo4OssSalesLogon(Long salesId, Long memberId, String salesEmail)
            throws OssLogonErrorException {
        CoreMemberInfo memberInfo = coreMemberInfoDAO.getByMemberId(memberId);
        if (memberInfo == null) {
            throw new OssLogonErrorException("后台登录失败 销售id:" + salesId + "  用户 id:" + memberId);
        }
        memberInfo.setMemberType(RoleFactory.ROLE_OSS_SALES);
        memberInfo.setEmail(salesEmail);
        return memberInfo;
    }

    /**
     * 创建cookieid 格式:20位随机数(包括数字和字母):当前时间(精确到微秒)
     * 
     * @return
     */
    public String buildNewCookieId() {
        return (RandomStringUtils.random(20, true, true)) + ":" + System.nanoTime();
    }

    /**
     * 创建sessionId,格式:ip地址:当前时间(精确到微秒)
     * 
     * @param ip
     * @return
     */
    public String buildNewSessionId(String ip) {
        return ip + ":" + System.nanoTime();
    }

    /**
     * 根据cookieId与sessionId查询用户登录信息
     * 
     * @param cookieId
     * @param sessionId
     * @return
     * @throws NotLogonException
     */
    public CoreMemberInfo getLogonInfoByEncryptCookieIdAndEncryptSessionId(String encryptCookieId,
            String encryptSessionId) throws NotLogonException {
        if (StringUtils.isBlank(encryptCookieId) || StringUtils.isBlank(encryptSessionId)) {
            CoreMemberInfo coreMemberInfo4NotLogon = new CoreMemberInfo();
            BaseRole role = new RoleNotLogon();
            coreMemberInfo4NotLogon.setRole(role);
            return coreMemberInfo4NotLogon;
        }
        String decodeCookieId = decodeCookieValue(encryptCookieId);
        String decodeSessionId = decodeCookieValue(encryptSessionId);
        LogonInfo logonInfo = logonInfoDao.getLogonInfo(decodeCookieId, decodeSessionId);
        if (logonInfo == null) {
            throw new NotLogonException("cookieId与sessionId无效，请重新登录");
        }
        Long memberId = logonInfo.getMemberId();
        CoreMemberInfo info = coreMemberInfoDAO.getByMemberId(memberId);
        info.setLogonInfo(logonInfo);
        return info;
    }

    /**
     * 对加密cookie进行解密
     * 
     * @param cookie
     * @return
     */
    protected String decodeCookieValue(String cookie) {
        return encryptService.decrypt(cookie, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
    }

    /**
     * 对cookie进行加密
     * 
     * @param cookie
     * @return
     */
    protected String encodeCookieValue(String cookie) {
        return encryptService.encrypt(cookie, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
    }

    /**
     * 创建用户登录信息记录
     * 
     * @param request
     * @param response
     * @param logonEmail
     * @param memberId
     * @return
     */
    protected LogonInfo createLogonInfo(HttpServletRequest request, HttpServletResponse response, String logonEmail,
            Long memberId) {
        String ip = IpUtil.getIpAddr(request);
        String newCookieId = buildNewCookieId();
        String newSessionId = buildNewSessionId(ip);
        LogonInfo logonInfo = new LogonInfo(newCookieId, newSessionId, logonEmail, memberId, ip);
        logonInfoDao.insert(logonInfo);
        CoreUserHis historyInfo = new CoreUserHis();
        historyInfo.setMemberId(memberId);
        historyInfo.setLoginIp(ip);
        Date date = new Date();
        historyInfo.setAddTime(date);
        historyInfo.setLoginTime(date);
        historyInfo.setUpdTime(date);
        historyInfo.setDelTime(date);
        historyInfo.setDelFlag("0");
        coreUserHisDAO.insertSelective(historyInfo);

        // 加密随机字符 用于cookieID
        String encryptCookieId = encodeCookieValue(newCookieId);
        String encryptSessionId = encodeCookieValue(newSessionId);
        CookieUtils.setSessionCookie(response, CookieUtils.COOKIE_KEY_COOKIE_ID, encryptCookieId);
        CookieUtils.setSessionCookie(response, CookieUtils.COOKIE_KEY_SESSION_ID, encryptSessionId);
        logonInfo.setLogonSuccess(true);
        return logonInfo;
    }

}
