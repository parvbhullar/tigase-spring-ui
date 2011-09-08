/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.auth.roles;

/**
 * RoleFactory.java 初始化Role的静态工厂类
 * 
 * @author baojun
 */
public abstract class RoleFactory {

    public static final int ROLE_NOT_LOGON = -1;
    public static final int ROLE_ALL = 99;
    public static final int ROLE_GENERAL = 0;
    public static final int ROLE_ADVERTISERS = 1;
    public static final int ROLE_DEVELOPERS = 2;
    public static final int ROLE_ADV_AND_DEV = 3;
    public static final int ROLE_AGENTS = 4;
    public static final int ROLE_SHOPPER = 5;
    public static final int ROLE_OSS_SALES = 6;

    /**
     * 对既是广告主又是开发者的用户而言当前使用角色是唯一的
     */
    public static final String CURRENT_ROLE_TYPE_ADV = "1";
    public static final String CURRENT_ROLE_TYPE_DEV = "2";

    /**
     * 会员类型 '0-无角色 1-广告商 2-开发者 3-广告商开发者 4-代理商 5-店铺主';
     */
    public static BaseRole getRole(int memberType) {
        BaseRole baseRole = null;
        switch (memberType) {
            case ROLE_ADVERTISERS :
                baseRole = new RoleAdvertisers();
                break;
            case ROLE_DEVELOPERS :
                baseRole = new RoleDevelopers();
                break;
            case ROLE_ADV_AND_DEV :
                baseRole = new RoleAdvAndDev();
                break;
            case ROLE_AGENTS :
                baseRole = new RoleAgents();
                break;
            case ROLE_SHOPPER :
                baseRole = new RoleShopper();
                break;
            case ROLE_OSS_SALES :
                baseRole = new RoleOssSales();
                break;
            case ROLE_GENERAL :
                baseRole = new RoleGeneral();
                break;
            default :
                baseRole = new RoleNotLogon();
        }
        return baseRole;
    }
}
