/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.exception;

/**
 * NotLogonException.java 用户未登录业务异常，用于控制登录检查流程，MVC框架捕获次异常，直接跳转至登录页面
 * 
 * @author baojun
 */
public class NotLogonException extends Exception {

    /**
     * 
     */
    public NotLogonException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public NotLogonException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public NotLogonException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public NotLogonException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
