/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import java.util.Collections;
import java.util.Map;

import com.mitian.airad.common.auth.compare.UrlMatcher;
import com.mitian.airad.common.utils.StringUtils;

/**
 * 处理特殊的URL，需要自己的自定义登录页面
 * 
 * @author zhoufengbo
 */
public class DefaultLoginUrlProcessor implements LoginUrlProcessor {
    /**
     * 正则匹配器,目前实现了三种正则样式(JAVA/Perl5/Ant)
     */
    private UrlMatcher matcher;

    /**
     * 正则路径匹配表
     */
    private Map<String, String> customizeUrlMap = Collections.emptyMap();

    public boolean isMeet(String requestUrl) {
        return StringUtils.isNotBlank(receiveLoginUrl(requestUrl));

    }

    public String receiveLoginUrl(String requestUrl) {
        if (matcher.requiresLowerCaseUrl()) {
            requestUrl = requestUrl.toLowerCase();
        }

        if (customizeUrlMap != null && customizeUrlMap.size() > 0) {
            for (String key : customizeUrlMap.keySet()) {
                if (matcher.pathMatchesUrl(key, requestUrl)) {
                    return customizeUrlMap.get(key);
                }
            }
        }
        return null;
    }

    /**
     * @return the matcher
     */
    public UrlMatcher getMatcher() {
        return matcher;
    }

    /**
     * @param matcher the matcher to set
     */
    public void setMatcher(UrlMatcher matcher) {
        this.matcher = matcher;
    }

    /**
     * @return the customizeUrlMap
     */
    public Map<String, String> getCustomizeUrlMap() {
        return customizeUrlMap;
    }

    /**
     * @param customizeUrlMap the customizeUrlMap to set
     */
    public void setCustomizeUrlMap(Map<String, String> customizeUrlMap) {
        this.customizeUrlMap = customizeUrlMap;
    }

}
