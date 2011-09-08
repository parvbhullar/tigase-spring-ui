/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

/**
 * 代表实例化类时失败的异常。
 * 
 * @author zhoufengob
 */
public class ClassInstantiationException extends Exception {
    private static final long serialVersionUID = 3258408422113555761L;

    /**
     * 构造一个空的异常.
     */
    public ClassInstantiationException() {
        super();
    }

    /**
     * 构造一个异常, 指明异常的详细信息.
     * 
     * @param message 详细信息
     */
    public ClassInstantiationException(String message) {
        super(message);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     * 
     * @param cause 异常的起因
     */
    public ClassInstantiationException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     * 
     * @param message 详细信息
     * @param cause 异常的起因
     */
    public ClassInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
