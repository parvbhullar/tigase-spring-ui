///*
// * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
// */
//package com.mitian.airad.common.auth.provider.jdbc;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.mitian.airad.common.auth.AuthException;
//import com.mitian.airad.common.auth.AuthenticationToken;
//import com.mitian.airad.common.auth.IUser;
//import com.mitian.airad.common.auth.provider.AuthProvider;
//import com.mitian.airad.common.codec.DigestedException;
//import com.mitian.airad.common.codec.EncryptService;
//import com.mitian.airad.common.utils.Assert;
//
///**
// * 数据库认证，验证用户名和密码是否正确
// */
//public class JDBCAuthProvider implements AuthProvider {
//    private static final Log logger = LogFactory.getLog(JDBCAuthProvider.class);
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private EncryptService encryptService;
//
//    public AuthenticationToken authenticate(AuthenticationToken token) throws AuthException {
//        String username = token.getUserName();
//        Assert.notNull(username, "用户名为空");
//        IUser user = retrieveUser(username);
//        Assert.notNull(user, "用户名或密码错误");
//
//        try {
//            boolean match =
//                    encryptService.matches(token.getPassword(), user.getPassword(), EncryptService.DIGEST_ALGORITHM.MD5
//                            .name());
//            Assert.isTrue(match, "用户名或密码错误");
//        }
//        catch (DigestedException ex) {
//            logger.error("MD5 digest user password fail  because " + ex.getMessage());
//            throw new AuthException("用户名或密码错误");
//        }
//
//        token.setUserId(user.getUserId());
//        token.setDefaultModel(user.getDefaultModel());
//        token.getModels().putAll(user.getModels());
//        return token;
//    }
//
//    /**
//     * 通过外部的服务获取用户
//     * 
//     * @param username
//     * @return
//     */
//    private IUser retrieveUser(String username) {
//        IUser loadedUser = null;
//        try {
//            loadedUser = userService.loadUserByUsername(username);
//        }
//        catch (Exception ex) {
//            logger.error("load user from database fail because " + ex.getMessage());
//            throw new AuthException("用户名或密码错误");
//        }
//        return loadedUser;
//
//    }
//
//    public boolean support(AuthenticationToken token) {
//        return true;
//    }
//
// }
