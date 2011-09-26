/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import java.util.Collections;
import java.util.List;

import com.mitian.airad.common.auth.compare.UrlMatcher;

/**
 * 缺省实现类,判断哪些URL可以忽略认证
 * 
 * @author zhoufengbo
 */
public class DefaultExcludeUrlProcessor implements ExcludeUrlProcessor {
    /**
     * 正则匹配器,目前实现了三种正则样式(JAVA/Perl5/Ant)
     */
    private UrlMatcher matcher;

    /**
     * 正则路径
     */
    private List<String> ignoreUrlList = Collections.emptyList();

    public boolean isExclude(String requestUrl) {
        if (matcher.requiresLowerCaseUrl()) {
            requestUrl = requestUrl.toLowerCase();
        }

        if (ignoreUrlList != null && ignoreUrlList.size() > 0) {
            for (String ignore : ignoreUrlList) {
                if (matcher.pathMatchesUrl(ignore, requestUrl)) {
                    return true;
                }
            }
        }
        return false;

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
     * @return the ignoreUrlList
     */
    public List<String> getIgnoreUrlList() {
        return ignoreUrlList;
    }

    /**
     * @param ignoreUrlList the ignoreUrlList to set
     */
    public void setIgnoreUrlList(List<String> ignoreUrlList) {
        this.ignoreUrlList = ignoreUrlList;
    }

}
