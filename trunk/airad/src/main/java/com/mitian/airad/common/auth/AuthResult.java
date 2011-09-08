/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth;

import java.util.HashMap;
import java.util.Map;

import com.mitian.airad.common.utils.StringUtils;

/**
 * 认证结果
 * 
 * @author zhoufengbo
 */
public class AuthResult {

    private String errorMessage;

    private boolean success = false;

    private Map models = new HashMap(4);
    private String defaultModelKey;

    /** 在models表中代表默认的model的key。 */
    private static final String DEFAULT_MODEL_KEY = "_defaultModel";

    /**
     * 取得默认model对象的key。
     * 
     * @return 默认model对象的key
     */
    public String getDefaultModelKey() {
        return StringUtils.defaultIfEmpty(defaultModelKey, DEFAULT_MODEL_KEY);
    }

    /**
     * 取得model对象。
     * <p>
     * 此调用相当于<code>getModels().get(DEFAULT_MODEL_KEY)</code>。
     * </p>
     * 
     * @return model对象
     */
    public Object getDefaultModel() {
        return models.get(getDefaultModelKey());
    }

    /**
     * 设置model对象。
     * <p>
     * 此调用相当于<code>getModels().put(DEFAULT_MODEL_KEY, model)</code>。
     * </p>
     * 
     * @param model model对象
     */
    public void setDefaultModel(Object model) {
        setDefaultModel(DEFAULT_MODEL_KEY, model);
    }

    /**
     * 设置model对象。
     * <p>
     * 此调用相当于<code>getModels().put(key, model)</code>。
     * </p>
     * 
     * @param key 字符串key
     * @param model model对象
     */
    public void setDefaultModel(String key, Object model) {
        defaultModelKey = StringUtils.defaultIfEmpty(key, DEFAULT_MODEL_KEY);
        models.put(key, model);
    }

    /**
     * 取得所有model对象。
     * 
     * @return model对象表
     */
    public Map getModels() {
        return models;
    }

    /**
     * 请求是否成功。
     * 
     * @return 如果成功，则返回<code>true</code>
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置请求成功标志。
     * 
     * @param success 成功标志
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 取得错误信息
     * 
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 设置错误信息
     * 
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
