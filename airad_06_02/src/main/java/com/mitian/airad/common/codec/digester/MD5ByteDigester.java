/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.digester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.digest.StandardByteDigester;

import com.mitian.airad.common.codec.DigestedException;

/**
 * MD5算法,一般强度摘要,针对Byte[]
 * 
 * @author zhoufengbo
 */
public class MD5ByteDigester implements Digester {

    private final StandardByteDigester digester;
    private static Log logger = LogFactory.getLog(MD5ByteDigester.class);

    public MD5ByteDigester() {
        digester = new StandardByteDigester();
        digester.initialize();
    }

    public byte[] digest(byte[] message) {

        try {
            return digester.digest(message);
        }
        catch (Exception ex) {
            logger.error("摘要异常", ex);
            throw new DigestedException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }
    }

    public String digest(String message) {

        throw new DigestedException("this is String Digester, not support byte[]");
    }

    public boolean matches(byte[] message, byte[] digest) {
        try {
            return digester.matches(message, digest);
        }
        catch (Exception ex) {
            logger.error("摘要异常", ex);
            throw new DigestedException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }
    }

    public boolean matches(String message, String digest) {
        throw new DigestedException("this is String Digester, not support byte[]");
    }

}
