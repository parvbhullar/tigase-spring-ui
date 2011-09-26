/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

/**
 * 可忽略的请求,除此之外都需求处理
 * 
 * @author zhoufengbo
 */
public interface ExcludeUrlProcessor {

    /**
     * @param requestUrl
     * @return
     */
    boolean isExclude(String requestUrl);

}
