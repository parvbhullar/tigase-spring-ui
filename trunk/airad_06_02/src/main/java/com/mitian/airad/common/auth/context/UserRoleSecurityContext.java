/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.context;

import com.mitian.airad.model.CoreMemberInfo;

/**
 * 安全上下文实现类
 * 
 * @author zhoufengbo
 */
public class UserRoleSecurityContext implements SecurityContext {

    /**
     * <p>
     * 环境信息
     * </p>
     * 
     * @see OperationEnvironment
     */
    private OperationEnvironment operationEnvironment;
    private CoreMemberInfo memberInfo;
    /**
     * 是否登录
     */
    private boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public OperationEnvironment getOperationEnvironment() {
        return operationEnvironment;
    }

    public void setOperationEnvironment(OperationEnvironment operationEnvironment) {
        this.operationEnvironment = operationEnvironment;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.common.auth.context.SecurityContext#getMemberInfo()
     */
    public CoreMemberInfo getMemberInfo() {
        return memberInfo;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.common.auth.context.SecurityContext#setMemberInfo(com.mitian.airad.model.CoreMemberInfo)
     */
    public void setMemberInfo(CoreMemberInfo coreMemberInfo) {
        memberInfo = coreMemberInfo;
    }

}
