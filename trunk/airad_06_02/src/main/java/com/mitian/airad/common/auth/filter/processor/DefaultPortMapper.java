/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mitian.airad.common.utils.Assert;

/**
 * @author zhoufengbo
 * @since 2010-11-10下午04:30:55
 */
public class DefaultPortMapper implements PortMapper {
    private Map<Integer, Integer> httpsPortMappings;

    public DefaultPortMapper() {
        httpsPortMappings = new HashMap<Integer, Integer>();
        httpsPortMappings.put(Integer.valueOf(80), Integer.valueOf(443));
        httpsPortMappings.put(Integer.valueOf(8080), Integer.valueOf(8443));
    }

    public Map<Integer, Integer> getTranslatedPortMappings() {
        return httpsPortMappings;
    }

    public Integer lookupHttpPort(Integer httpsPort) {
        Iterator<Integer> iter = httpsPortMappings.keySet().iterator();

        while (iter.hasNext()) {
            Integer httpPort = iter.next();

            if (httpsPortMappings.get(httpPort).equals(httpsPort)) {
                return httpPort;
            }
        }

        return null;
    }

    public Integer lookupHttpsPort(Integer httpPort) {
        return httpsPortMappings.get(httpPort);
    }

    public Map<Integer, Integer> getHttpsPortMappings() {
        return httpsPortMappings;
    }

    public void setHttpsPortMappings(Map<Integer, Integer> httpsPortMappings) {
        Assert.notNull(httpsPortMappings, "A valid list of HTTPS port mappings must be provided");
        this.httpsPortMappings.clear();
        for (Map.Entry<Integer, Integer> entry : httpsPortMappings.entrySet()) {
            Integer httpPort = entry.getKey();
            Integer httpsPort = entry.getValue();
            if ((httpPort.intValue() < 1) || (httpPort.intValue() > 65535) || (httpsPort.intValue() < 1)
                    || (httpsPort.intValue() > 65535)) {
                throw new IllegalArgumentException("one or both ports out of legal range: " + httpPort + ", "
                        + httpsPort);
            }
            this.httpsPortMappings.put(httpPort, httpsPort);
        }
        if (this.httpsPortMappings.size() < 1) {
            throw new IllegalArgumentException("must map at least one port");
        }
    }

    public void setPortMappings(Map<String, String> newMappings) {
        Assert.notNull(newMappings, "A valid list of HTTPS port mappings must be provided");
        httpsPortMappings.clear();
        for (Map.Entry<String, String> entry : newMappings.entrySet()) {
            Integer httpPort = Integer.valueOf(entry.getKey());
            Integer httpsPort = Integer.valueOf(entry.getValue());
            if ((httpPort.intValue() < 1) || (httpPort.intValue() > 65535) || (httpsPort.intValue() < 1)
                    || (httpsPort.intValue() > 65535)) {
                throw new IllegalArgumentException("one or both ports out of legal range: " + httpPort + ", "
                        + httpsPort);
            }
            httpsPortMappings.put(httpPort, httpsPort);
        }
        if (httpsPortMappings.size() < 1) {
            throw new IllegalArgumentException("must map at least one port");
        }
    }
}
