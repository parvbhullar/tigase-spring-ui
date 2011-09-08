/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.exception.OperationNotSupportException;
import com.mitian.airad.model.CoreUserHis;

/**
 * AbstractRole.java
 * 
 * @author Administrator
 */
public abstract class BaseRole implements OperationOnRole {
    /**
     * 该权限是由哪些附属权限id组织而成
     */
    protected Map<String, String> roleDetails;
    /**
     * 权限类前缀
     */
    private static final String CLASS_PACKAGE = "com.mitian.airad.web.auth.roles";
    /**
     * 权限名称以及对于的状态
     */
    private static final Map<String, String> ALL_ROLES_DEFINED = new HashMap<String, String>();
    static {
        ALL_ROLES_DEFINED.put("000000", "RoleGeneral;000000_普通用户#1");
        ALL_ROLES_DEFINED.put("000001", "RoleAdvertisers;000001_广告主#1");
        ALL_ROLES_DEFINED.put("000002", "RoleAdvertisers;000001_广告主#2");
        ALL_ROLES_DEFINED.put("000010", "RoleDevelopers;000010_开发者#1");
        ALL_ROLES_DEFINED.put("000020", "RoleDevelopers;000010_开发者#2");
        ALL_ROLES_DEFINED.put("000011", "RoleAdvAndDev;000001_广告主#1,000010_开发者#1");
        ALL_ROLES_DEFINED.put("000012", "RoleAdvAndDev;000001_广告主#1,000010_开发者#2");
        ALL_ROLES_DEFINED.put("000021", "RoleAdvAndDev;000001_广告主#2,000010_开发者#1");
        ALL_ROLES_DEFINED.put("000022", "RoleAdvAndDev;000001_广告主#2,000010_开发者#2");
        ALL_ROLES_DEFINED.put("000100", "RoleAgents;000100_代理商#1");
        ALL_ROLES_DEFINED.put("000200", "RoleAgents;000100_代理商#2");
    }

    /**
     * 初始化权限
     * 
     * @param roleValue
     * @return
     */
    @Deprecated
    public static BaseRole initRoleDetail(String roleValue) {
        BaseRole role = null;
        // 根据权限值，查找对应的权限信息
        String roleDefined = ALL_ROLES_DEFINED.get(roleValue);
        if (roleDefined == null) {
            return role;
        }
        // 得到对应的权限类名称以及相关状态
        String[] roleClassAndDesc = roleDefined.split(";");
        // 组织具体的类，用于反射生成对应的权限类
        String roleClassName = CLASS_PACKAGE + "." + roleClassAndDesc[0];
        String[] roleDetails = roleClassAndDesc[1].split(",");
        try {
            Map<String, String> roleMaps = new HashMap<String, String>(roleDetails.length + 1);
            // 加入默认的普通用户，方便用于访问权限的判断
            roleMaps.put("000000", "普通用户#1");
            for (String detail : roleDetails) {
                String[] roleValueAndDesc = detail.split("_");
                roleMaps.put(roleValueAndDesc[0], roleValueAndDesc[1]);
            }
            // 得到构造器
            Constructor<?> constructor = Class.forName(roleClassName).getConstructor(Map.class);
            // 创建对于的权限类
            if (constructor != null) {
                role = (BaseRole) constructor.newInstance(roleMaps);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * 该角色是否支持方法的访问
     * 
     * @param role
     * @return
     * @throws NotLogonException
     */
    public boolean isSupportMethod(int[] roleIds) throws NotLogonException {
        if (ArrayUtils.contains(roleIds, RoleFactory.ROLE_ALL)) {
            return true;
        }
        if (ArrayUtils.contains(roleIds, getRoleType())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 获取角色类型,参见 {@link RoleFactory#ROLE_ADV_AND_DEV }等类型定义
     * 
     * @return
     */
    public abstract int getRoleType();

    /**
     * 获取不同角色登录后跳转URL
     * 
     * @throws NotLogonException
     */
    public abstract ModelAndView getLogonAfterPage(String nextUrl) throws NotLogonException;

    /**
     * 角色判断，子类覆盖
     * 
     * @return
     */
    public boolean isAdvAndDev() {
        return false;
    }

    public boolean isAdvertisers() {
        return false;
    }

    public boolean isAgents() {
        return false;
    }

    public boolean isDev() {
        return false;
    }

    public boolean isGeneral() {
        return false;
    }

    public boolean isShopper() {
        return false;
    }

    public boolean isOssSales() {
        return false;
    }

    /**
     * 根据会员角色获取所能创建的报表类型,对于既是开发者又是广告主的角色需根据当前使用角色判断
     * 
     * @param currentRoleType
     * @return
     * @throws NotLogonException
     */
    public abstract String getReportTypes(String currentRoleType) throws NotLogonException;

    /**
     * 获取在页面显示的会员角色名称
     * 
     * @return
     */
    public abstract String getShowName();

    /**
     * 当前角色如何保存广告 三种情况 对于开发者或商铺店主 无法保存广告 对于没有通过认证的广告主 保存为草稿 对于认证通过的广告主 正常保存
     * 
     * @return
     * @throws OperationNotSupportException
     */
    public boolean isSaveAdDirect() throws OperationNotSupportException {
        throw new OperationNotSupportException("current role :" + getShowName() + "can not save ad");
    }

    /**
     * 判断是否首次登陆 除商铺店主角色外，对于首次登陆的用户都需引导至信息完善页面
     * 
     * @param his
     * @return
     */
    public boolean needCompleteMemberInfo(CoreUserHis his) {
        if (his == null || his.isFirstLogon()) {
            return true;
        }
        return false;
    }

}
