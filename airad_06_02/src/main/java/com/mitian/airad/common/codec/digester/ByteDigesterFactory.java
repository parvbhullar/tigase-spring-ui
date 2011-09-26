/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.digester;

import com.mitian.airad.common.utils.StringUtils;

/**
 * 单例模式& 工厂模式
 * 
 * @author zhoufengbo
 */
public final class ByteDigesterFactory implements DigesterFactory {

    private static ByteDigesterFactory factory = new ByteDigesterFactory();

    public static ByteDigesterFactory getInstance() {
        return factory;
    }

    private ByteDigesterFactory() {

    }

    public Digester getDigester(String algorithm) {
        if (StringUtils.equals(algorithm, DigesterFactory.TYPE.MD5.name())) {
            return new MD5ByteDigester();
        }
        if (StringUtils.equals(algorithm, DigesterFactory.TYPE.SHA256.name())) {
            return new SHA256ByteDigester();
        }
        return null;
    }
}
