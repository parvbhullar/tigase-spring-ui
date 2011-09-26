/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import com.mitian.airad.common.utils.math.NumberUtils;

/**
 * 转换工具类
 * 
 * @author zhoufengbo
 */
public abstract class ConverterUtils {

    private ConverterUtils() {
        super();
    }

    /**
     * 判断输入值是否是数字类型，不是则用缺省值替换
     * 
     * @param in 输入字符
     * @param defaultValue 缺省替换字符:"0"/"9999" 等
     * @return
     */
    public static String converterForNumber(String in, String defaultValue) {
        if (StringUtils.isBlank(in)) {
            return defaultValue;
        }
        if (NumberUtils.isNumber(in)) {
            return in;
        }
        return defaultValue;

    }
}
