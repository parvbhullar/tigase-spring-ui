/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.common.exception.NotLogonException;

/**
 * RoleNotLogon.java 未登录角色
 * 
 * @author baojun
 */
public class RoleNotLogon extends BaseRole {

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getReportTypes(java.lang.String)
     */
    @Override
    public String getReportTypes(String currentRoleType) throws NotLogonException {
        throw new NotLogonException("该角色目前没登录");
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_NOT_LOGON;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getShowName()
     */
    @Override
    public String getShowName() {
        return "未登录";
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getLogonAfterPage(java.lang.String)
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) throws NotLogonException {
        throw new NotLogonException("该角色目前没登录");
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#isSupportMethod(int[])
     */
    @Override
    public boolean isSupportMethod(int[] roleIds) throws NotLogonException {
        throw new NotLogonException("current user need logon");
    }
}
