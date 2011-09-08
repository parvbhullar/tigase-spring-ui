/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.common.exception.OperationNotSupportException;
import com.mitian.airad.common.utils.StringUtils;

/**
 * 广告主角色
 * 
 * @author Administrator
 */
public class RoleAdvertisers extends BaseRole {

    @Override
    public boolean isAdvertisers() {
        return true;
    }

    public RoleAdvertisers() {
        roleDetails = roleDetails;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getShowName()
     */
    @Override
    public String getShowName() {
        return "广告商";
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getReportTypes(java.lang.String)
     */
    @Override
    public String getReportTypes(String currentRoleType) {
        return org.apache.commons.lang.StringUtils.join(new String[]{Constants.REPORT_TYPE_AD_CAMPAIGN,
                Constants.REPORT_TYPE_AD_GROUOP, Constants.REPORT_TYPE_AD}, ",");
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_ADVERTISERS;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getLogonAfterPage(java.lang.String)
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) {
        if (StringUtils.isNotBlank(nextUrl)) {
            return new ModelAndView("redirect:" + nextUrl);
        }
        else {
            return new ModelAndView("redirect:/personal.do?action=advlist");
        }
    }

    @Override
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        return true;
    }
}
