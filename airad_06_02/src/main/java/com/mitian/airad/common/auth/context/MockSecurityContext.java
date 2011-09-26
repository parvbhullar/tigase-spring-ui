/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.context;

import java.util.List;

import com.mitian.airad.model.CoreMemberInfo;

/**
 * Mock,测试用途
 * 
 * @author zhoufengbo
 */
public class MockSecurityContext implements SecurityContext {

    public OperationEnvironment getOperationEnvironment() {
        return null;
    }

    public void setOperationEnvironment(OperationEnvironment opEnv) {
    }

    public List<Long> getRoleIds() {
        return null;
    }

    public boolean isLogin() {
        return true;
    }

    public void setLogin(boolean isLogin) {

    }

    public void setRoleIds(List<Long> roleIds) {
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.common.auth.context.SecurityContext#getMemberInfo()
     */
    public CoreMemberInfo getMemberInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.common.auth.context.SecurityContext#setMemberInfo(com.mitian.airad.model.CoreMemberInfo)
     */
    public void setMemberInfo(CoreMemberInfo coreMemberInfo) {
        // TODO Auto-generated method stub

    }

}
