/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.encrypter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.mitian.airad.common.codec.EncryptException;

/**
 *DES,针对String加密/解密
 * 
 * @author zhoufengbo
 * @since 2010-09-20
 */
public class DESStringEncrypter implements Encrypter {
    private final StandardPBEStringEncryptor encryptor;
    private static final Log logger = LogFactory.getLog(DESStringEncrypter.class);

    public DESStringEncrypter() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(Encrypter.TYPE.PBEWithMD5AndDES.name());
        encryptor.setPassword(Encrypter.PASSWORD.INSKYE.name());
    }

    public byte[] decrypt(byte[] encryptedMessage) throws EncryptException {

        throw new EncryptException("this is Byte[] Digester, not support String");
    }

    public byte[] encrypt(byte[] message) throws EncryptException {

        throw new EncryptException("this is Byte[] Digester, not support String");
    }

    public String encrypt(String message) throws EncryptException {
        try {
            return encryptor.encrypt(message);
        }
        catch (Exception ex) {
            logger.error("加密[" + message + "]异常", ex);
            throw new EncryptException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }
    }

    public String decrypt(String encryptedMessage) throws EncryptException {
        try {
            return encryptor.decrypt(encryptedMessage);
        }
        catch (Exception ex) {
            logger.error("解密[" + encryptedMessage + "]异常", ex);
            throw new EncryptException(ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage());
        }

    }
}
