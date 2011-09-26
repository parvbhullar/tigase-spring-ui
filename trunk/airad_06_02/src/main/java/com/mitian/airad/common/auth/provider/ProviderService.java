/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.auth.provider;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.mitian.airad.common.auth.AbstractAuthService;
import com.mitian.airad.common.auth.AuthException;
import com.mitian.airad.common.auth.AuthenticationToken;

/**
 * 认证Provider，整个各种认证方式
 * 
 * @author zhoufengbo
 */
public class ProviderService extends AbstractAuthService {

    private static Log logger = LogFactory.getLog(ProviderService.class);
    private List<AuthProvider> providers = Collections.emptyList();

    @Override
    protected AuthenticationToken doAuthentication(AuthenticationToken token) {

        AuthenticationToken result = null;

        for (AuthProvider provider : getProviders()) {
            logger.debug("Authentication attempt using " + provider.getClass().getName());

            try {
                if (provider.support(token)) {
                    result = provider.authenticate(token);
                }
            }
            catch (Exception ex) {
                logger.error("Authentication fail because " + ex.getMessage());
                throw new AuthException(ex.getMessage());
            }
        }
        return result;
    }

    public List<AuthProvider> getProviders() {
        return providers;
    }

    @SuppressWarnings("unchecked")
    public void setProviders(List providers) {
        Assert.notNull(providers, "无法认证");
        this.providers = providers;
    }

}
