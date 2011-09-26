/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;

import com.mitian.BaseTest;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.model.CoreMemberInfo;

/**
 * LogonServiceTest.java
 * 
 * @author baojun
 */
public class LogonServiceTest extends BaseTest {

    @Autowired
    private LogonService logonService;

    /**
     * Test method for {@link com.mitian.airad.service.LogonService#buildNewCookieId()}.
     */
    @Test
    public void testBuildNewCookieId() {
        String cookieId = logonService.buildNewCookieId();
        System.out.println(cookieId);
    }

    /**
     * Test method for {@link com.mitian.airad.service.LogonService#buildNewSessionId(java.lang.String)}.
     */
    @Test
    public void testBuildNewSessionId() {
        String sessionId = logonService.buildNewSessionId("127.0.0.1");
        System.out.println(sessionId);
    }

    // @Test
    public void testsendNoticeMail4TryLogonOver5Times() {
        String email = "toby941@gmail.com";
        int times = 70;
        String password = "111222";
        String errorCaptcha = "23535";
        String rightCaptcha = "34sdf";
        logonService.sendNoticeMail4TryLogonOver5Times(email, times, "127.0.0.1", password, errorCaptcha, rightCaptcha);
    }

    @Test
    public void testTxDoLogon() {
        HttpServletRequest request = EasyMock.createMock(HttpServletRequestWrapper.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponseWrapper.class);
        EasyMock.expect(request.getHeader("x-forwarded-for")).andReturn("127.0.0.1");
        EasyMock.replay(request);
        CoreMemberInfo memberInfo = logonService.txDoLogon("toby941@gmail.com", "111111", request, response);
        System.out.println(memberInfo.getLogonInfo().isLogonSuccess());
    }

    @Test
    @ExpectedException(NotLogonException.class)
    public void testGetLogonInfoByEncryptCookieIdAndEncryptSessionId() throws NotLogonException {
        String cookieId = "Zvqo85MQ9jnFigk0Dw0c:525128875259261";
        String sessionId = "127.0.0.1:525128875267677";
        String encryptCookieId = logonService.encodeCookieValue(cookieId);
        String encryptSessionId = logonService.encodeCookieValue(sessionId);
        System.out.println("encryptCookieId: " + encryptCookieId);
        System.out.println("encryptSessionId: " + encryptSessionId);
        CoreMemberInfo coreMemberInfo =
                logonService.getLogonInfoByEncryptCookieIdAndEncryptSessionId(encryptCookieId, encryptSessionId);
        System.out.println(coreMemberInfo.getEmail());
    }

    @Test
    public void testTxDoLogout() {

    }

    /**
     * @return the logonService
     */
    public LogonService getLogonService() {
        return logonService;
    }

    /**
     * @param logonService the logonService to set
     */
    public void setLogonService(LogonService logonService) {
        this.logonService = logonService;
    }
}
