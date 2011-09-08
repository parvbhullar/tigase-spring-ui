/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Https实现，即匹配规则的所有请求必须走Https协议
 * 
 * @author zhoufengbo
 * @since 2010-11-10下午04:02:43
 */
public class SecureChannelFilter extends AbstractChannelFilter {
    private String scheme = "https";
    private int standardPort = 443;

    @Override
    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public int getStandardPort() {
        return standardPort;
    }

    public void setStandardPort(int standardPort) {
        this.standardPort = standardPort;
    }

    @Override
    protected Integer getMappedPort(Integer mapFromPort) {
        return getPortMapper().lookupHttpsPort(mapFromPort);
    }

    @Override
    protected boolean isMeet(HttpServletRequest request, HttpServletResponse response) {
        return !request.isSecure();
    }
}
