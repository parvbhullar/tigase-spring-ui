/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivyinfo.framework.common.exception;

/**
 *
 * @author 张砚
 */
public class IvyinfoException extends Exception {

    /**
     * Creates a new instance of <code>IvyinfoException</code> without
     * detail message.
     */
    public IvyinfoException() {

    }

    /**
     * Constructs an instance of <code>IvyinfoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IvyinfoException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>IvyinfoException</code> with the
     * specified exception.
     *
     * @param cause Throwable
     */
    public IvyinfoException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an instance of <code>IvyinfoException</code> with the
     * specified detail message.
     *
     * @param msg String
     * @param cause Throwable
     */
    public IvyinfoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
