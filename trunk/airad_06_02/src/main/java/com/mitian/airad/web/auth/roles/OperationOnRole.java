/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.common.exception.NotLogonException;

/**
 * OperationOnRole.java
 * 
 * @author Administrator
 */
public interface OperationOnRole {
    /**
     * 登陆后跳转页面
     * 
     * @param nextUrl
     * @return
     * @throws NotLogonException
     */
    ModelAndView getLogonAfterPage(String nextUrl) throws NotLogonException;

}
