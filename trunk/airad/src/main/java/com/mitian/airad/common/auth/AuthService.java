/*
 * Copyright 2010 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth;

import javax.servlet.http.HttpServletResponse;

/**
 * 认证管理器，在用户登录时，提供身份认证
 * 
 * @author zhoufengbo
 */
public interface AuthService {

    /**
     * @param response
     * @param token
     * @return
     */
    AuthResult authenticate(HttpServletResponse response, AuthenticationToken token);

}
