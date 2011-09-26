/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

/**
 * 处理特殊的URL，需要自己的自定义登录页面
 * 
 * @author zhoufengbo
 */
public interface LoginUrlProcessor {

    /**
     * 判断是否匹配
     * 
     * @param requestUrl
     * @return
     */
    boolean isMeet(String requestUrl);

    /**
     * 获取自定义的登录页面
     * 
     * @param requestUrl
     * @return
     */
    String receiveLoginUrl(String requestUrl);

}
