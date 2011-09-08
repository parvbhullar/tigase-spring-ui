/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

/**
 * 只处理指定的特定条件的请求,除此之外都不处理
 * 
 * @author zhoufengbo
 */
public interface IncludeUrlProcessor {

    public boolean isinclude(String requestUrl);

}
