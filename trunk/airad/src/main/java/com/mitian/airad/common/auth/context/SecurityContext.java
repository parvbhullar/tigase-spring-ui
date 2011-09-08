package com.mitian.airad.common.auth.context;

import com.mitian.airad.model.CoreMemberInfo;

/**
 * 线程安全，存放用户和环境信息
 * 
 * @author zhoufengbo
 */
public interface SecurityContext {

    public CoreMemberInfo getMemberInfo();

    /**
     * <p>
     * 获取环境信息
     * </p>
     * 
     * @return OperationEnvironment
     */
    public OperationEnvironment getOperationEnvironment();

    /**
     * <p>
     * 设置环境信息
     * </p>
     * 客户端IP ,serverName, 访问URL
     */
    public void setOperationEnvironment(OperationEnvironment opEnv);

    /**
     * 判断是否已经登录过
     * 
     * @return
     */
    public boolean isLogin();

    /**
     * 设置登录标识
     * 
     * @param isLogin
     */
    public void setLogin(boolean isLogin);

    /**
     * @param coreMemberInfo
     */
    public void setMemberInfo(CoreMemberInfo coreMemberInfo);

}
