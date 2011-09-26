/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.digester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.digest.StandardStringDigester;

import com.mitian.airad.common.codec.DigestedException;

/**
 * MD5算法,一般强度摘要，针对String
 * 
 * @author zhoufengbo
 */
public class MD5StringDigester implements Digester {

    private final StandardStringDigester digester;
    private static Log logger = LogFactory.getLog(MD5StringDigester.class);

    public MD5StringDigester() {
        digester = new StandardStringDigester();
        digester.initialize();
    }

    public byte[] digest(byte[] message) {
        throw new DigestedException("this is String Digester, not support byte[]");
    }

    public String digest(String message) {

        try {
            return digester.digest(message);
        }
        catch (Exception ex) {
            logger.error("摘要异常", ex);
            throw new DigestedException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }
    }

    public boolean matches(byte[] message, byte[] digest) {
        throw new DigestedException("this is String Digester, not support byte[]");
    }

    public boolean matches(String message, String digest) {

        try {
            return digester.matches(message, digest);
        }
        catch (Exception ex) {
            logger.error("摘要异常", ex);
            throw new DigestedException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }
    }

}
