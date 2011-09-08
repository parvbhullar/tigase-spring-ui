/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.filter;

import java.util.Set;

/**
 * 后台权限系统扩展,根据UserId获取列表只跟工具相关的权限
 * 
 * @author zhoufengbo
 */
public interface IAuthPermissionService {

    /**
     * 获取只跟列表工具相关的权限 ，结果都是funcid
     * 
     * @param userId
     * @return
     */
    Set<String> getToolListPermissionByUserId(long userId);

    /**
     * 获取Controller的权限，ControllerClass:ControllerMethod
     * 
     * @param userId
     * @return
     */
    Set<String> getControllerPermissionByUserId(long userId);

    /**
     * @param parFuncid
     * @return
     */
    boolean isRegisterFuncId(String funcid);

}
