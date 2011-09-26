/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import java.util.Properties;

/**
 * 在cache store provider中使用的读取properties的简易工具。
 * <p>
 * 支持fail-fast：如果指定了非法的值，则立即出错。
 * </p>
 * 
 * @author zhoufengbo
 */
public abstract class PropertiesUtils {

    /**
     * 工具类无法实例化
     */
    private PropertiesUtils() {
        super();
    }

    public static final String getString(Properties props, String key, String defaultValue) {
        String strValue = null;

        if ((props != null) && (key != null)) {
            strValue = StringUtils.trimToNull(props.getProperty(key));
        }

        if (strValue == null) {
            strValue = defaultValue;
        }

        return strValue;
    }

    public static final boolean getBoolean(Properties props, String key, boolean defaultValue) {
        String strValue = getString(props, key, null);
        boolean value;

        if (strValue == null) {
            value = defaultValue;
        }
        else {
            if ("true".equalsIgnoreCase(strValue)) {
                value = true;
            }
            else if ("false".equalsIgnoreCase(strValue)) {
                value = false;
            }
            else {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }

    public static final int getInt(Properties props, String key, int defaultValue) {
        String strValue = getString(props, key, null);
        int value;

        if (strValue == null) {
            value = defaultValue;
        }
        else {
            try {
                value = Integer.parseInt(strValue);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }

    public static final long getLong(Properties props, String key, long defaultValue) {
        String strValue = getString(props, key, null);
        long value;

        if (strValue == null) {
            value = defaultValue;
        }
        else {
            try {
                value = Long.parseLong(strValue);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }
}
