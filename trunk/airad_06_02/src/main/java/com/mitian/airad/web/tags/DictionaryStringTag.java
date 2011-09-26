/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.mitian.airad.common.utils.SpringContextUtils;
import com.mitian.airad.refreshable.DictionaryRefreshable;

/**
 * DictionaryTag.java
 * 
 * @author liyuhang
 */
public class DictionaryStringTag extends TagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 5639759681566470399L;
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(DictionaryStringTag.class);
    private String typeKey;
    private String valueKey;
    private static DictionaryRefreshable cache;

    static {
        cache = (DictionaryRefreshable) SpringContextUtils.getBean("dictionaryRefreshable");
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String html = getValueByTypeAndKey(typeKey, valueKey);
        try {
            pageContext.getOut().write(html.toString());
        }
        catch (IOException e) {
            LOGGER.error("tag DictionaryStringTag error", e);
        }
        return EVAL_PAGE;
    }

    private String getValueByTypeAndKey(String typeKey, String valueKey) {
        return cache.getValueByTypeKeyAndValueKey(typeKey, valueKey);
    }

    /**
     * @return the typeKey
     */
    public String getTypeKey() {
        return typeKey;
    }

    /**
     * @param typeKey the typeKey to set
     */
    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    /**
     * @return the valueKey
     */
    public String getValueKey() {
        return valueKey;
    }

    /**
     * @param valueKey the valueKey to set
     */
    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }
}
