/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import javax.servlet.ServletRequest;

import com.mitian.airad.common.utils.Assert;

/**
 * @author zhoufengbo
 * @since 2010-11-10下午04:31:26
 */
public class DefaultPortResolver implements PortResolver {

    private PortMapper portMapper = new DefaultPortMapper();

    public PortMapper getPortMapper() {
        return portMapper;
    }

    public int getServerPort(ServletRequest request) {
        int serverPort = request.getServerPort();
        Integer portLookup = null;

        String scheme = request.getScheme().toLowerCase();

        if ("http".equals(scheme)) {
            portLookup = portMapper.lookupHttpPort(Integer.valueOf(serverPort));

        }
        else if ("https".equals(scheme)) {
            portLookup = portMapper.lookupHttpsPort(Integer.valueOf(serverPort));
        }

        if (portLookup != null) {
            // IE 6 bug
            serverPort = portLookup.intValue();
        }

        return serverPort;
    }

    public void setPortMapper(PortMapper portMapper) {
        Assert.notNull(portMapper, "portMapper cannot be null");
        this.portMapper = portMapper;
    }

}
