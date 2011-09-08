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
public class StringEncrypterFactory implements EncrypterFactory {

    private static StringEncrypterFactory factory = new StringEncrypterFactory();

    public static StringEncrypterFactory getInstance() {
        return factory;
    }

    private StringEncrypterFactory() {

    }

    public Encrypter getEncrypter(String algorithm) {
        if (StringUtils.equals(algorithm, EncrypterFactory.TYPE.PBEWithMD5AndDES.name())) {
            return new DESStringEncrypter();
        }
        if (StringUtils.equals(algorithm, EncrypterFactory.TYPE.PBEWithMD5AndTripleDES.name())) {
            return new TriDESStringEncrypter();
        }
        return null;
    }
}
