/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.exception;

/**
 * OssLogonErrorException.java 后台用户登录失败异常
 * 
 * @author baojun
 */
public class OssLogonErrorException extends Exception {

    /**
     * 
     */
    public OssLogonErrorException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public OssLogonErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public OssLogonErrorException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public OssLogonErrorException(Throwable cause) {
        super(cause);
    }

}
