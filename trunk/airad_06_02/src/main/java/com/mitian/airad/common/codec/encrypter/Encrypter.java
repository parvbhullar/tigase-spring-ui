/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.encrypter;

/**
 * 加密/解密器，采用固定的基于口令的密码
 * 
 * @author zhoufengbo
 * @since 2010-09-17
 * @version 1.0
 */
public interface Encrypter {

    enum PASSWORD {
        INSKYE
    }

    enum TYPE {
        PBEWithMD5AndDES, PBEWithMD5AndTripleDES
    }

    byte[] encrypt(byte[] message);

    byte[] decrypt(byte[] encryptedMessage);

    String encrypt(String message);

    String decrypt(String encryptedMessage);
}
