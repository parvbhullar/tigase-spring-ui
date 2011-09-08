/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http实现，即匹配规则的所有请求必须走Http协议
 * 
 * @author zhoufengbo
 * @since 2010-11-10下午04:02:43
 */
public class InSecureChannelFilter extends AbstractChannelFilter {

    private String scheme = "http";
    private int standardPort = 80;

    @Override
    protected boolean isMeet(HttpServletRequest request, HttpServletResponse response) {
        return request.isSecure();
    }

    @Override
    public Integer getMappedPort(Integer mapFromPort) {
        return getPortMapper().lookupHttpPort(mapFromPort);
    }

    @Override
    protected String getScheme() {
        return scheme;
    }

    @Override
    protected int getStandardPort() {
        return standardPort;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setStandardPort(int standardPort) {
        this.standardPort = standardPort;
    }

}
