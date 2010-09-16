package com.linkage.framework.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAccessDecisionManager implements AccessDecisionManager{

	final Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		logger.info("开始对资源[{}]进行访问权限验证......",object.toString());
		//请求的资源不需要权限
		if(configAttributes==null){
			logger.info("访问资源[{}]不需要权限!",object.toString());
			return;
		}
		Iterator<ConfigAttribute> iterator=configAttributes.iterator();
		while(iterator.hasNext()){
			ConfigAttribute configAttribute=iterator.next();
			String roleName=((SecurityConfig)configAttribute).getAttribute();
			for(GrantedAuthority ga:authentication.getAuthorities()){
				if(ga.getAuthority().equals(roleName)){
					logger.info("用户具备访问资源[{}]所需要的权限[{}]!",object.toString(),roleName);
					return;
				}
			}
		}
		logger.info("用户不具备访问资源[{}]所需要的权限!",object.toString());
		throw new AccessDeniedException("该用户不具备访问资源["+object.toString()+"]的权限");
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public boolean supports(ConfigAttribute arg0) {
		return true;
	}
}
