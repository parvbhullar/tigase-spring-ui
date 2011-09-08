/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import java.util.Collections;
import java.util.List;

import com.mitian.airad.common.auth.compare.UrlMatcher;

/**
 * 缺省实现类,判断哪些URL需要处理
 * 
 * @author zhoufengbo
 */
public class DefaultIncludeUrlProcessor implements IncludeUrlProcessor {

    /**
     * 正则匹配器,目前实现了三种正则样式(JAVA/Perl5/Ant)
     */
    private UrlMatcher matcher;

    /**
     * 正则路径
     */
    private List<String> includeUrlList = Collections.emptyList();

    public boolean isinclude(String requestUrl) {
        if (matcher.requiresLowerCaseUrl()) {
            requestUrl = requestUrl.toLowerCase();
        }

        if (includeUrlList != null && includeUrlList.size() > 0) {
            for (String include : includeUrlList) {
                if (matcher.pathMatchesUrl(include, requestUrl)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * @return the includeUrlList
     */
    public List<String> getIncludeUrlList() {
        return includeUrlList;
    }

    /**
     * @param includeUrlList the includeUrlList to set
     */
    public void setIncludeUrlList(List<String> includeUrlList) {
        this.includeUrlList = includeUrlList;
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
}
