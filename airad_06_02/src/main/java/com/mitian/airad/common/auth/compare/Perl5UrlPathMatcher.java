/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.compare;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

import com.mitian.airad.common.utils.Assert;
import com.mitian.airad.common.utils.StringUtils;

/**
 * Perl5样式URL匹配
 * 
 * @author zhoufengbo
 */
public class Perl5UrlPathMatcher implements UrlMatcher {
    private static final Log LOGGER = LogFactory.getLog(Perl5UrlPathMatcher.class);
    private boolean requiresLowerCaseUrl = true;
    private Perl5Compiler compiler;
    private Perl5Matcher matcher;

    public Perl5UrlPathMatcher() {
        this(true);
    }

    public Perl5UrlPathMatcher(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
        compiler = new Perl5Compiler();
        matcher = new Perl5Matcher();
    }

    public boolean requiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public String getUniversalMatchPattern() {
        return "/.*";
    }

    public boolean pathMatchesUrl(String path, String url) {
        if (StringUtils.isBlank(url)) {
            return true;
        }
        Assert.notNull(path);
        if (requiresLowerCaseUrl) {
            path = path.toLowerCase();
        }
        try {
            Pattern pattern = compiler.compile(path, Perl5Compiler.MULTILINE_MASK);
            return matcher.contains(url, pattern);
        }
        catch (MalformedPatternException e) {
            LOGGER.error("正则匹配[pattern=" + path + ",url=" + url + "]异常", e);
            return false;
        }
        catch (Exception e) {
            LOGGER.error("正则匹配[pattern=" + path + ",url=" + url + "]异常", e);
            return false;
        }

    }

}
