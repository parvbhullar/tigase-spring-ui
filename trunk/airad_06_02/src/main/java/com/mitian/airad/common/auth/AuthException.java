package com.mitian.airad.common.auth;

/**
 * @author zhoufengbo
 */
public class AuthException extends RuntimeException {
    private static final long serialVersionUID = 8578335547393723982L;

    public AuthException() {
        super();
    }

    /**
     * @param msg
     */
    public AuthException(String msg) {
        super(msg);
    }

    /**
     * @param t
     */
    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
