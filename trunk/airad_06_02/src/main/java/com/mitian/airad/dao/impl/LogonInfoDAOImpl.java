/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mitian.airad.dao.LogonInfoDao;
import com.mitian.airad.dao.support.CustomSqlMapClientDaoSupport;
import com.mitian.airad.model.LogonInfo;

/**
 * LogonInfoDaoImpl.java
 * 
 * @author baojun
 */
@Repository
public class LogonInfoDAOImpl extends CustomSqlMapClientDaoSupport implements LogonInfoDao {

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.LogonInfoDao#deleteByCookieIdAndSessionId(java.lang.String, java.lang.String)
     */
    public void deleteByCookieIdAndSessionId(String cookieId, String sessionId) {
        LogonInfo logonInfo = new LogonInfo(cookieId, sessionId);
        getSqlMapClientTemplate().update("LOGON_INFO.update4Logout", logonInfo);

    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.LogonInfoDao#getLogonInfo(java.lang.String, java.lang.String)
     */
    public LogonInfo getLogonInfo(String cookieId, String sessionId) {
        LogonInfo logonInfo = new LogonInfo(cookieId, sessionId);
        List<LogonInfo> infoList =
                getSqlMapClientTemplate().queryForList("LOGON_INFO.getByCookieIdAndCookieId", logonInfo);
        if (!infoList.isEmpty()) {
            return infoList.get(0);
        }
        else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.LogonInfoDao#insert(com.mitian.airad.model.LogonInfo)
     */
    public void insert(LogonInfo logonInfo) {
        getSqlMapClientTemplate().insert("LOGON_INFO.add", logonInfo);
    }
}
