/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.compare;

/**
 * URL匹配
 * 
 * @author zhoufengbo
 */
public interface UrlMatcher {

    boolean pathMatchesUrl(String path, String url);

    String getUniversalMatchPattern();

    boolean requiresLowerCaseUrl();

}
