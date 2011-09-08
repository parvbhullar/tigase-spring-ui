/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

/**
 * 认证异常
 * 
 * @author zhoufengbo
 */
public class AuthenticationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -7131614311725819483L;

    /**
     * 
     */
    public AuthenticationException() {
    }

    /**
     * @param message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
