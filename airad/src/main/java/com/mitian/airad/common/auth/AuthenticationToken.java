/*
 * Copyright 2010 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.common.utils.builder.EqualsBuilder;
import com.mitian.airad.common.utils.builder.HashCodeBuilder;
import com.mitian.airad.common.utils.builder.ToStringBuilder;
import com.mitian.airad.common.utils.builder.ToStringStyle;
import com.mitian.airad.web.auth.roles.BaseRole;

/**
 * 认证凭证 , 收集界面上传递过来的参数, 目前考虑到的有用户名、密码和动态口令信息
 * 
 * @author zhoufengbo
 * @since 2010-09-27
 */
@SuppressWarnings("serial")
public class AuthenticationToken implements Serializable {
    private boolean authenticated = false;
    /** 在models表中代表默认的model的key。 */
    private static final String DEFAULT_MODEL_KEY = "_defaultModel";

    public static final int AUTH_TYPE_BASE = 1;
    public static final int AUTH_TYPE_MIDDLE = 2;
    public static final int AUTH_TYPE_FULL = 3;

    private Long userId = null;
    private String userName;
    private String password;
    private String key;
    private Map models = new HashMap(4);
    private String defaultModelKey;
    private BaseRole baseRole;

    @Override
    public boolean equals(Object objCompared) {
        if (!(objCompared instanceof AuthenticationToken)) {
            return false;
        }

        AuthenticationToken rhs = (AuthenticationToken) objCompared;

        return new EqualsBuilder().append(userName, rhs.userName).append(password, rhs.password).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", userId).append("userName",
                userName).append("password", password).append("key", key).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(15, 19).append(userName).append(password).toHashCode();
    }

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
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * @param authenticated the authenticated to set
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the baseRole
     */
    public BaseRole getBaseRole() {
        return baseRole;
    }

    /**
     * @param baseRole the baseRole to set
     */
    public void setBaseRole(BaseRole baseRole) {
        this.baseRole = baseRole;
    }
}
