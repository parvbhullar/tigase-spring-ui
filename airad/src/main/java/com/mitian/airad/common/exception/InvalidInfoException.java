/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.exception;

/**
 * InvalidInfoException.java 业务校验失败时抛出此异常，由框架统一处理
 * 
 * @author baojun
 */
public class InvalidInfoException extends Exception {

    /**
     * 
     */
    public InvalidInfoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public InvalidInfoException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public InvalidInfoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidInfoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
