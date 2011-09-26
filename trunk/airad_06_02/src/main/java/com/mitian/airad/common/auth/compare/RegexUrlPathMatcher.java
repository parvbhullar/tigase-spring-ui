/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.compare;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mitian.airad.common.utils.Assert;

/**
 * JAVA样式URL匹配
 * 
 * @author zhoufengbo
 */
public class RegexUrlPathMatcher implements UrlMatcher {

    private boolean requiresLowerCaseUrl = true;
    private static final Log LOGGER = LogFactory.getLog(RegexUrlPathMatcher.class);

    public RegexUrlPathMatcher() {
        this(true);
    }

    public RegexUrlPathMatcher(boolean requiresLowerCaseUrl) {
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
        try {
            Pattern pattern = Pattern.compile(path, Pattern.MULTILINE);
            return pattern.matcher(url).find();
        }
        catch (Exception e) {
            LOGGER.error("正则匹配[pattern=" + path + ",url=" + url + "]异常", e);
            return false;
        }

    }

    public String getUniversalMatchPattern() {
        return "/.*";
    }

    public boolean requiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }
}
