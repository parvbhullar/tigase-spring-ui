/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.exception.OperationNotSupportException;
import com.mitian.airad.common.utils.StringUtils;

/**
 * 普通会员角色
 * 
 * @author baojun
 */
public class RoleGeneral extends BaseRole {

    @Override
    public boolean isGeneral() {
        return true;
    }

    public RoleGeneral() {
        super();
    }

    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) {
        // ModelAndView mv = new ModelAndView("redirect:/app.do?action=listApp");
        ModelAndView mv = new ModelAndView("redirect:/personal.do?action=advlist");
        if (StringUtils.isNotBlank(nextUrl)) {
            mv = new ModelAndView("redirect:" + nextUrl);
        }
        return mv;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getShowName()
     */
    @Override
    public String getShowName() {
        return "普通会员";
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
        return RoleFactory.ROLE_GENERAL;
    }

    @Override
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        return false;
    }
}
