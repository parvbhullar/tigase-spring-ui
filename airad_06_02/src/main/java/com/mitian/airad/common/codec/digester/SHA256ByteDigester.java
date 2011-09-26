/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.digester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.digest.StandardByteDigester;

import com.mitian.airad.common.codec.DigestedException;

/**
 * SHA256算法,256位摘要,针对Byte[]
 * 
 * @author zhoufengbo
 */
public class SHA256ByteDigester implements Digester {

    private static Log logger = LogFactory.getLog(SHA256ByteDigester.class);
    private final StandardByteDigester digester;

    public SHA256ByteDigester() {
        digester = new StandardByteDigester();
        digester.setAlgorithm("SHA-256");
        digester.setIterations(100000);
        digester.setSaltSizeBytes(16);
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
