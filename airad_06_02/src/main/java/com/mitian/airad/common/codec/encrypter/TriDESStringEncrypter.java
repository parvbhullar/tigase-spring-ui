/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.encrypter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.mitian.airad.common.codec.EncryptException;

/**
 *三重DES,针对String加密/解密
 * 
 * @author zhoufengbo
 * @since 2010-09-20
 */
public class TriDESStringEncrypter implements Encrypter {
    private static Log logger = LogFactory.getLog(TriDESStringEncrypter.class);
    private final StandardPBEStringEncryptor encryptor;

    public TriDESStringEncrypter() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(Encrypter.TYPE.PBEWithMD5AndTripleDES.name());
        encryptor.setPassword(Encrypter.PASSWORD.INSKYE.name());
    }

    public byte[] decrypt(byte[] encryptedMessage) {

        throw new EncryptException("this is Byte[] Digester, not support String");
    }

    public byte[] encrypt(byte[] message) {

        throw new EncryptException("this is Byte[] Digester, not support String");
    }

    public String encrypt(String message) {
        try {
            return encryptor.encrypt(message);
        }
        catch (Exception ex) {
            logger.error("加密[" + message + "]异常", ex);
            throw new EncryptException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }

    }

    public String decrypt(String encryptedMessage) {
        try {
            return encryptor.decrypt(encryptedMessage);
        }
        catch (Exception ex) {
            logger.error("解密[" + encryptedMessage + "]异常", ex);
            throw new EncryptException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }

    }

}
