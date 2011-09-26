/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.component.openapi;

import org.springframework.stereotype.Service;

/**
 * TaobaoOpenApiComponent.java
 * 
 * @author liyuhang
 */
@Service
public class TaobaoOpenApiComponent {
    private String appKey;
    private String appSecret;
    private String apiUrl;
    private String callBackUrl;
    private String oauthAuthUrl;
    private String oauthAccessTokenUrl;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public void setOauthAuthUrl(String oauthAuthUrl) {
        this.oauthAuthUrl = oauthAuthUrl;
    }

    public void setOauthAccessTokenUrl(String oauthAccessTokenUrl) {
        this.oauthAccessTokenUrl = oauthAccessTokenUrl;
    }

    // getter begin
    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getOauthAuthUrl() {
        return oauthAuthUrl + "?response_type=code&client_id=" + appKey + "&redirect_uri=" + callBackUrl;
    }

    public String getOauthAccessTokenUrl() {
        return oauthAccessTokenUrl;
    }
}
