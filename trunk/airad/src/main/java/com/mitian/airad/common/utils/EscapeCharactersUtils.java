/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * EscapeCharacters.java
 * 
 * @author Administrator
 */
public class EscapeCharactersUtils {
    public static final String escapeCharacters(final String str) {
        // return (StringUtils.isNotBlank(str) && str.indexOf("'") != -1) ? str.replaceAll("'", "\\\\'") : "";
        return StringUtils.isNotBlank(str) ? str.replace("%", "/%").replace("_", "/_").replace("'", "\\\\'") : "";
    }

}
