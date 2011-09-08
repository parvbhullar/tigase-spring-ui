/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.model;

import java.util.Calendar;
import java.util.Date;

/**
 * LogonInfo.java
 * 
 * @author baojun
 */
public class LogonInfo {

    private Long recId;
    // 格式 20位随机数+当前时间 微单位
    private String cookieId;
    // 格式 ip地址+当前时间微秒单位
    private String sessionId;
    private Date logonTime;
    private String logonIp;
    private Long memberId;
    private String logonEmail;
    private boolean isLogonSuccess = false;
    private String status;
    private String logonFailTipMessage;

    public LogonInfo(String cid, String sid, String email, Long mid, String ip) {
        super();
        cookieId = cid;
        sessionId = sid;
        logonEmail = email;
        memberId = mid;
        logonIp = ip;
        logonTime = Calendar.getInstance().getTime();
        // 默认status为1 表示正常 0代表删除
        status = "1";
    }

    public LogonInfo() {
        super();
    }

    /**
     * @param cookieId2
     * @param sessionId2
     */
    public LogonInfo(String cookieId, String sessionId) {
        super();
        this.cookieId = cookieId;
        this.sessionId = sessionId;
    }

    /**
     * @return the recId
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * @param recId the recId to set
     */
    public void setRecId(Long recId) {
        this.recId = recId;
    }

    /**
     * @return the cookieId
     */
    public String getCookieId() {
        return cookieId;
    }

    /**
     * @param cookieId the cookieId to set
     */
    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the logonTime
     */
    public Date getLogonTime() {
        return logonTime;
    }

    /**
     * @param logonTime the logonTime to set
     */
    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    /**
     * @return the logonIp
     */
    public String getLogonIp() {
        return logonIp;
    }

    /**
     * @param logonIp the logonIp to set
     */
    public void setLogonIp(String logonIp) {
        this.logonIp = logonIp;
    }

    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the logonEmail
     */
    public String getLogonEmail() {
        return logonEmail;
    }

    /**
     * @param logonEmail the logonEmail to set
     */
    public void setLogonEmail(String logonEmail) {
        this.logonEmail = logonEmail;
    }

    /**
     * @return the isLogonSuccess
     */
    public boolean isLogonSuccess() {
        return isLogonSuccess;
    }

    /**
     * @param isLogonSuccess the isLogonSuccess to set
     */
    public void setLogonSuccess(boolean isLogonSuccess) {
        this.isLogonSuccess = isLogonSuccess;
    }

    /**
     * @return the logonFailTipMessage
     */
    public String getLogonFailTipMessage() {
        return logonFailTipMessage;
    }

    /**
     * @param logonFailTipMessage the logonFailTipMessage to set
     */
    public void setLogonFailTipMessage(String logonFailTipMessage) {
        this.logonFailTipMessage = logonFailTipMessage;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
