/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.CoreUserHis;

/**
 *店铺主角色
 * 
 * @author baojun
 */
public class RoleShopper extends BaseRole {

    public RoleShopper() {
        super();
    }

    /**
     * 覆盖父类的登录跳转方法 开发者类型的会员登录后跳转到应用列表
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) {
        ModelAndView mv = new ModelAndView("redirect:/personal.do?action=index");
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
        return "商铺店主";
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

    @Override
    public boolean isShopper() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_SHOPPER;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#needCompleteMemberInfo(com.mitian.airad.model.CoreUserHis)
     */
    @Override
    public boolean needCompleteMemberInfo(CoreUserHis his) {
        return false;
    }
}
