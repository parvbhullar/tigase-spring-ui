/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.provider;

import com.mitian.airad.common.auth.AuthenticationToken;

/**
 * 验证接口
 * 
 * @author zhoufengbo
 */
public interface AuthProvider {

    /**
     * @param token
     * @return
     */
    AuthenticationToken authenticate(AuthenticationToken token);

    boolean support(AuthenticationToken token);

}
