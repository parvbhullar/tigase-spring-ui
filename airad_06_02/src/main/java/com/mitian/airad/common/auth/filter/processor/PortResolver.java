/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter.processor;

import javax.servlet.ServletRequest;

/**
 * @author zhoufengbo
 * @since 2010-11-10下午04:21:35
 */
public interface PortResolver {

    /**
     * 当前服务器正使用的端口
     * 
     * @param request
     * @return
     */
    int getServerPort(ServletRequest request);

}
