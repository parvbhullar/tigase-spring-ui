/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.springframework.stereotype.Service;

/**
 * DeploymentContextService.java
 * 
 * @author baojun
 */
@Service
public class DeploymentContextService {

    private static final String DEPLOYMENT_TYPE_OSS = "oss";
    private static final String DEPLOYMENT_TYPE_AIRAD = "airad";

    private String currentDeploymentType;

    /**
     * 请求hermes广告banner预览URL， 可配置便于开发测试生产环境切换
     */
    private String hermesApplicationAdBannerRequestUrl;

    /**
     * 请求hermes广告内容预览URL， 可配置便于开发测试生产环境切换
     */
    private String hermesApplicationAdRequestUrl;

    /**
     * @return the currentDeploymentType
     */
    public String getCurrentDeploymentType() {
        return currentDeploymentType;
    }

    /**
     * @param currentDeploymentType the currentDeploymentType to set
     */
    public void setCurrentDeploymentType(String currentDeploymentType) {
        this.currentDeploymentType = currentDeploymentType;
    }

    /**
     * 判断当前项目是否用于后台发布
     * 
     * @return
     */
    public boolean isOSSDeployType() {
        return DEPLOYMENT_TYPE_OSS.equalsIgnoreCase(currentDeploymentType);
    }

    /**
     * 判断当前项目是否用于前台发布
     * 
     * @return
     */
    public boolean isAiradDeployType() {
        return DEPLOYMENT_TYPE_AIRAD.equalsIgnoreCase(currentDeploymentType);

    }

    /**
     * @return the hermesApplicationAdRequestUrl
     */
    public String getHermesApplicationAdRequestUrl() {
        return hermesApplicationAdRequestUrl;
    }

    /**
     * @param hermesApplicationAdRequestUrl the hermesApplicationAdRequestUrl to set
     */
    public void setHermesApplicationAdRequestUrl(String hermesApplicationAdRequestUrl) {
        this.hermesApplicationAdRequestUrl = hermesApplicationAdRequestUrl;
    }

    /**
     * @return the hermesApplicationAdBannerRequestUrl
     */
    public String getHermesApplicationAdBannerRequestUrl() {
        return hermesApplicationAdBannerRequestUrl;
    }

    /**
     * @param hermesApplicationAdBannerRequestUrl the hermesApplicationAdBannerRequestUrl to set
     */
    public void setHermesApplicationAdBannerRequestUrl(String hermesApplicationAdBannerRequestUrl) {
        this.hermesApplicationAdBannerRequestUrl = hermesApplicationAdBannerRequestUrl;
    }
}
