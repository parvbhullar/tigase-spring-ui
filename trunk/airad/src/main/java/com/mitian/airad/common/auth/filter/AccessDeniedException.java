/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

/**
 * 访问拒绝异常
 * 
 * @author zhoufengbo
 */
public class AccessDeniedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1330881568608719431L;

    /**
     * 
     */
    public AccessDeniedException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public AccessDeniedException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public AccessDeniedException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
