/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

/**
 * 匹配需要匿名登录的URL
 * 
 * @author zhoufengbo
 * @since 2010-10-19下午01:35:39
 */
public interface AnonymousUrlProcessor {

    /**
     * 判断是否匹配
     * 
     * @param requestUrl
     * @return
     */
    boolean isMeet(String requestUrl);

}
