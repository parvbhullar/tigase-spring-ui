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
 * 代理商角色
 * 
 * @author baojun
 */
public class RoleAgents extends BaseRole {

    @Override
    public boolean isAgents() {
        return true;
    }

    public RoleAgents() {
    }

    /**
     * 覆盖父类的登录跳转方法 代理商类型的会员登录后跳转到代理商关系列表
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) {
        ModelAndView mv = new ModelAndView("redirect:/agentRelation.do?action=agencyList");
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
        return "代理商";
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getReportTypes(java.lang.String)
     */
    @Override
    public String getReportTypes(String currentRoleType) throws NotLogonException {
        return org.apache.commons.lang.StringUtils.join(new String[]{Constants.REPORT_TYPE_AD_CAMPAIGN,
                Constants.REPORT_TYPE_AD_GROUOP, Constants.REPORT_TYPE_AD, Constants.REPORT_TYPE_AGENTS}, ",");
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_AGENTS;
    }

    @Override
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        return true;
    }
}
