/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import java.util.Collections;
import java.util.List;

import com.mitian.airad.common.auth.compare.UrlMatcher;

/**
 * 匹配需要匿名登录的URL
 * 
 * @author zhoufengbo
 * @since 2010-10-19下午01:35:39
 */
public class DefaultAnonymousUrlProcessor implements AnonymousUrlProcessor {
    private UrlMatcher matcher;
    private List<String> anonymousUrlList = Collections.emptyList();

    public boolean isMeet(String requestUrl) {

        if (matcher.requiresLowerCaseUrl()) {
            requestUrl = requestUrl.toLowerCase();
        }

        if (anonymousUrlList != null && anonymousUrlList.size() > 0) {
            for (String anonymous : anonymousUrlList) {
                if (matcher.pathMatchesUrl(anonymous, requestUrl)) {
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
     * @return the anonymousUrlList
     */
    public List<String> getAnonymousUrlList() {
        return anonymousUrlList;
    }

    /**
     * @param anonymousUrlList the anonymousUrlList to set
     */
    public void setAnonymousUrlList(List<String> anonymousUrlList) {
        this.anonymousUrlList = anonymousUrlList;
    }
}
