/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.exception.OperationNotSupportException;

/**
 * RoleOssSales.java 后台销售人员
 * 
 * @author baojun
 */
public class RoleOssSales extends BaseRole {

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getLogonAfterPage(java.lang.String)
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) throws NotLogonException {
        // TODO Auto-generated method stub
        return null;
    }

    public RoleOssSales() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getReportTypes(java.lang.String)
     */
    @Override
    public String getReportTypes(String currentRoleType) throws NotLogonException {
        return org.apache.commons.lang.StringUtils.join(new String[]{Constants.REPORT_TYPE_AD_CAMPAIGN,
                Constants.REPORT_TYPE_AD_GROUOP, Constants.REPORT_TYPE_AD}, ",");
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_OSS_SALES;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getShowName()
     */
    @Override
    public String getShowName() {
        return "销售人员";
    }

    @Override
    public boolean isOssSales() {
        return true;
    }

    @Override
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        return true;
    }
}
