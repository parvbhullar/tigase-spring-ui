/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.exception;

/**
 * OperatorNotSupportException.java 前台会员角色和操作不匹配异常
 * 
 * @author baojun
 */
public class OperationNotSupportException extends Exception {

    /**
     * 
     */
    public OperationNotSupportException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public OperationNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public OperationNotSupportException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public OperationNotSupportException(Throwable cause) {
        super(cause);
    }

}
