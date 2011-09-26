/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.compare;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.mitian.airad.common.utils.Assert;

/**
 * Ant样式URL匹配
 * 
 * @author zhoufengbo
 */
public class AntUrlPathMatcher implements UrlMatcher {

    private boolean requiresLowerCaseUrl = true;
    private PathMatcher pathMatcher = new AntPathMatcher();

    public AntUrlPathMatcher() {
        this(true);
    }

    public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public boolean pathMatchesUrl(String path, String url) {
        Assert.notNull(path);
        if (requiresLowerCaseUrl) {
            path = path.toLowerCase();
        }
        return pathMatcher.match(path, url);
    }

    public String getUniversalMatchPattern() {
        return "/**";
    }

    public boolean requiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[requiresLowerCase='" + requiresLowerCaseUrl + "']";
    }
}
