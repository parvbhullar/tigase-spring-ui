/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.common.exception.OperationNotSupportException;
import com.mitian.airad.common.utils.StringUtils;

/**
 * 广告主开发者角色
 * 
 * @author Administrator
 */
public class RoleAdvAndDev extends BaseRole {

    @Override
    public boolean isAdvAndDev() {
        return true;
    }

    public RoleAdvAndDev() {
    }

    /**
     * 覆盖父类的登录跳转方法 开发者广告主类型的会员登录后跳转到应用列表
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) {
        if (StringUtils.isNotBlank(nextUrl)) {
            return new ModelAndView("redirect:" + nextUrl);
        }
        else {
            return new ModelAndView("redirect:/personal.do?action=developlist");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getShowName()
     */
    @Override
    public String getShowName() {
        return "广告商与开发者";
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getReportTypes(java.lang.String)
     */
    @Override
    public String getReportTypes(String currentRoleType) {
        if (RoleFactory.CURRENT_ROLE_TYPE_DEV.equals(currentRoleType)) {
            return Constants.REPORT_TYPE_APP;
        }
        else {
            return org.apache.commons.lang.StringUtils.join(new String[]{Constants.REPORT_TYPE_AD_CAMPAIGN,
                    Constants.REPORT_TYPE_AD_GROUOP, Constants.REPORT_TYPE_AD}, ",");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_ADV_AND_DEV;
    }

    @Override
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        return true;
    }
}
