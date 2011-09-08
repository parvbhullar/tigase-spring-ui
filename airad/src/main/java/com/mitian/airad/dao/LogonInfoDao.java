/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao;

import com.mitian.airad.model.LogonInfo;

/**
 * LogonInfoDao.java
 * 
 * @author baojun
 */
public interface LogonInfoDao {

    /**
     * 创建logon记录
     * 
     * @param logonInfo
     */
    public void insert(LogonInfo logonInfo);

    /**
     * 根据解密后的cookieId与sessionId查找登录信息
     * 
     * @param decodeCookieId
     * @param decodeSessionId
     * @return
     */
    public LogonInfo getLogonInfo(String decodeCookieId, String decodeSessionId);

    /**
     * 根据cookieId与sessionId删除登录控制记录
     * 
     * @param cookieId
     * @param sessionId
     */
    public void deleteByCookieIdAndSessionId(String cookieId, String sessionId);

}
