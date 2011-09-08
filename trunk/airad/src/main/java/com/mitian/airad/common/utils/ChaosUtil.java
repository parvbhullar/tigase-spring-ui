/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

/**
 * IdChaosUtil.java 利用进制转换 进行数字混淆
 * 
 * @author baojun
 */
public class ChaosUtil {
    private static final Logger log = Logger.getLogger(ChaosUtil.class);
    private static long CHAOS_FACTOR = 814611L;
    private static int HEX = 19;

    /**
     * 对字符串进行混淆，字符串必须是有效整数数字
     * 
     * @param id
     * @return
     */
    public static String chaos(String id) {
        if (StringUtils.isEmpty(id)) {
            return StringUtils.EMPTY;
        }
        else if (!NumberUtils.isNumber(id)) {
            return StringUtils.EMPTY;
        }
        return chaos(Long.valueOf(id));
    }

    /**
     * 对数字进行混淆
     * 
     * @param id
     * @return
     */
    public static String chaos(Long id) {
        if (null == id || id <= 0) {
            return StringUtils.EMPTY;
        }
        return Long.toString(id * CHAOS_FACTOR, HEX);
    }

    /**
     * 对字符串进行反混淆，解析错误返回null
     * 
     * @param id
     * @return
     */
    public static Long unChaos(String id) {
        Long result = null;
        try {
            if (StringUtils.isEmpty(id)) {
                return null;
            }
            Long value = Long.parseLong(id, HEX);
            result = value / CHAOS_FACTOR;
        }
        catch (Exception e) {
            log.error("unChaos error id:" + id, e);
            result = null;
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(ChaosUtil.chaos(1L));
        System.out.println(ChaosUtil.chaos(100L));
        System.out.println(ChaosUtil.unChaos("64ea5"));
        System.out.println(ChaosUtil.unChaos("1dh1a06"));
    }
}
