/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec.digester;

/**
 * 摘要通用接口
 * 
 * @author zhoufengbo
 * @since 2010-09-17
 * @version 1.0
 */
public interface Digester {

    byte[] digest(byte[] message);

    boolean matches(byte[] message, byte[] digest);

    String digest(String message);

    boolean matches(String message, String digest);

}
