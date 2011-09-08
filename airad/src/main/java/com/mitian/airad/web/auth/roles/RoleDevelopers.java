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
 * 开发者角色
 * 
 * @author baojun
 */
public class RoleDevelopers extends BaseRole {

    @Override
    public boolean isDev() {
        return true;
    }

    public RoleDevelopers() {
    }

    /**
     * 覆盖父类的登录跳转方法 开发者类型的会员登录后跳转到应用列表
     */
    @Override
    public ModelAndView getLogonAfterPage(String nextUrl) {
        // ModelAndView mv = new ModelAndView("redirect:/app.do?action=listApp");
        ModelAndView mv = new ModelAndView("redirect:/personal.do?action=developlist");
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
        return "开发者";
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getReportTypes(java.lang.String)
     */
    @Override
    public String getReportTypes(String currentRoleType) throws NotLogonException {
        return Constants.REPORT_TYPE_APP;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.auth.roles.BaseRole#getRoleType()
     */
    @Override
    public int getRoleType() {
        return RoleFactory.ROLE_DEVELOPERS;
    }

    @Override
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        return false;
    }

}
