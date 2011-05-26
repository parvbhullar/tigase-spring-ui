package com.zy.im.core;

/**
 * Represents an exception that occurs handling a <code>Message</code>.
 *
 * @version $Id: MessageException.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public class MessageException extends Exception {

    /**
     * Creates a new instance of <code>MessageException</code> without detail
     * message.
     */
    public MessageException() {
    }

    /**
     * Constructs an instance of <code>MessageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message
     */
    public MessageException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>MessageException</code> with the
     * specified cause.
     *
     * @param cause the cause
     */
    public MessageException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an instance of <code>MessageException</code> with the
     * specified detail message and the given cause.
     *
     * @param msg the detail message
     * @param cause the cause
     */
    public MessageException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
