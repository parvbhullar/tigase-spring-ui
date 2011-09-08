/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import com.mitian.airad.model.CoreMemberInfo;

/**
 * 前台或者后台系统扩展,根据UserId获取User信息
 * 
 * @author zhoufengbo
 */
public interface IAuthUserService {

    /**
     * @param cookieId
     * @param sessionId
     * @return
     */
    public CoreMemberInfo getLogonInfoByCookieIdAndSessionId(String cookieId, String sessionId);

}
