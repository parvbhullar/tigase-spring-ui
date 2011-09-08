/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.encrypter;

import com.mitian.airad.common.utils.StringUtils;

/**
 * 单例模式& 工厂模式
 * 
 * @author zhoufengbo
 */
public class ByteEncrypterFactory implements EncrypterFactory {

    private static ByteEncrypterFactory factory = new ByteEncrypterFactory();

    public static ByteEncrypterFactory getInstance() {
        return factory;
    }

    private ByteEncrypterFactory() {

    }

    public Encrypter getEncrypter(String algorithm) {
        if (StringUtils.equals(algorithm, EncrypterFactory.TYPE.PBEWithMD5AndDES.name())) {
            return new DESByteEncrypter();
        }
        if (StringUtils.equals(algorithm, EncrypterFactory.TYPE.PBEWithMD5AndTripleDES.name())) {
            return new TriDESByteEncrypter();
        }
        return null;
    }
}
