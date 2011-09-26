/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.utils;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.mitian.airad.common.codec.DefaultEncryptService;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.utils.SpringContextUtils;

/**
 * ELFunctionUtils.java 封装用于JSP页面EL表达式调用的静态方法
 * 
 * @author baojun
 */
public abstract class ELFunctionUtils {

    private static final String DEPLOY_TIME_STR = String.valueOf(Calendar.getInstance().getTime().getTime());

    /**
     * 为css与js资源文件链接地址添加时间戳，以应用发布时间为更新周期,防止浏览器缓存静态资源文件
     * 
     * @return
     */
    public static final String cacheTimeToStr() {
        return DEPLOY_TIME_STR;
    }

    private static final String ERROR_TIP_TEMPLETE = "<div><div class=\"wrBox\"><div class=\"wr\" >{0}</div></div>";

    /**
     * 输出页面错误提示信息<br/>
     * ${}
     * 
     * @param errorMap
     * @param key
     * @return
     */
    public static final String errorTip(Map<String, Object> errorMap, String key) {
        if (errorMap != null && StringUtils.isNotBlank(key)) {
            String errorTip = (String) errorMap.get(key);
            if (StringUtils.isNotBlank(errorTip)) {
                return MessageFormat.format(ERROR_TIP_TEMPLETE, errorTip);
            }

        }
        return StringUtils.EMPTY;
    }

    /**
     * 给用户id加密
     * 
     * @param id
     * @return
     */
    public static final String encode(Long id) {
        DefaultEncryptService service = SpringContextUtils.getBeanByClazz(DefaultEncryptService.class);
        if (id != null) {
            return service.encrypt(String.valueOf(id), EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
        }
        else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(ELFunctionUtils.cacheTimeToStr());
    }
}
