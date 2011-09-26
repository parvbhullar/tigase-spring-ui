/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

/**
 * @author zhoufengbo
 * @since 2010-11-10下午04:19:28
 */
public interface PortMapper {
    /**
     * 查找HTTPS端口对应的HTTP端口
     * 
     * @param httpsPort
     * @return
     */
    Integer lookupHttpPort(Integer httpsPort);

    /**
     * 查找HTTP端口对应的HTTPS端口
     * 
     * @param httpPort
     * @return
     */
    Integer lookupHttpsPort(Integer httpPort);

}
